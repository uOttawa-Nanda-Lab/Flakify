import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jface.text.Document;

public class FlakifySmellsDetector {
	private static final List<String> assert_methods = Arrays.asList("assertArrayEquals", "assertEquals", "assertFalse", "assertNotNull", "assertNotSame", "assertNull", "assertSame", "assertThat", "assertTrue", "fail");
	List<String> setup_files_and_paths = new ArrayList<String>();
	List<String> static_non_final_variables = new ArrayList<>();

	String full_production_class_name = "";
	String full_test_class_name = "";
	String production_class_name_without_package = "";
	String test_class_name_without_package = "";
	String first_package_qualifier = "";
	String package_name = "";

	boolean visited = false;
	public ASTParser createNewParser(Document code) {
		 ASTParser parser = ASTParser.newParser(AST.JLS16);
		 parser.setSource(code.get().toCharArray());
		 parser.setKind(ASTParser.K_COMPILATION_UNIT);
		 parser.setResolveBindings(true);
		 parser.setEnvironment(null, new String[] {""}, new String[] {"UTF-8"}, true);
		 parser.setUnitName("");
		 return parser;
	}
	
	public void checkSetupMethod(CompilationUnit cu){
		 setup_files_and_paths = new ArrayList<String>();
	  	 cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration method_node) {
			   // Check if method name is 'setUp' or a @Before annotation exists (for JUnit 4) 
			   if ((method_node.getName().toString().equals("setUp") || String.valueOf(method_node.modifiers()).contains("@Before")) && method_node.getBody() != null) {
				   method_node.getBody().accept(new ASTVisitor() {
						@Override
						public boolean visit(ClassInstanceCreation n) {
							IMethodBinding owner = n.resolveConstructorBinding();
							if (owner != null && owner.getDeclaringClass() != null && owner.getDeclaringClass().getQualifiedName().equals("java.io.File")) {
								if (n.arguments().size() == 1 && n.arguments().get(0) != null)
									setup_files_and_paths.add(n.arguments().get(0).toString());
							}
							return super.visit(n);
						}
					});
			   }
		       return false;
			}
	  	 });
    }
    
	public void checkVariableModifiers(CompilationUnit cu){
		 static_non_final_variables = new ArrayList<>();
	  	 cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(VariableDeclarationStatement node) {
				int m = node.getModifiers();
               if((m & Modifier.STATIC)==Modifier.STATIC && (m & Modifier.FINAL)!=Modifier.FINAL) {
            	   static_non_final_variables.addAll(node.fragments());
               }
				return super.visit(node);
			}
			@Override
			public boolean visit(FieldDeclaration  node) {
               int m = node.getModifiers();
               if((m & Modifier.STATIC)==Modifier.STATIC && (m & Modifier.FINAL)!=Modifier.FINAL) {
                   List<VariableDeclarationFragment> fragments = node.fragments();
                   for(VariableDeclarationFragment fragment : fragments) {
                	   static_non_final_variables.add(fragment.getName().toString());
                   }
               }
			   return super.visit(node);
			}
	  	 });
	}


	public String extractProductionClassName(String full_test_class_name){
        String test_class_name = full_test_class_name.substring(full_test_class_name.lastIndexOf("/") + 1);
        String production_class_name_tmp = test_class_name.replace("Test", "");
        String production_class_name_split[] = production_class_name_tmp.split("[.]");
        String full_production_class_name = production_class_name_split[production_class_name_split.length - 1];
        return full_production_class_name;
	}
    
	public ASTNode getFullStatement(ASTNode parent) {
		// Retain full code statement
		while(!(parent instanceof Statement || parent.getParent() == null))
			parent = parent.getParent();
		return parent;
	}
	public void detectRetainTestSmells(String source_code, FileWriter output_file, String full_test_case_name, String test_cases_full_code_path, String test_cases_preprocessed_code_path, String test_case_class_name, String test_case_method_name, String project_name, String flaky, String version) throws IOException  {  
		 Document document = new Document(source_code);
		 ASTParser parser = createNewParser(document);
		 final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		 checkSetupMethod(cu);
		 checkVariableModifiers(cu);
		 
		 // ** for Eager Testing, Indirect Testing, and Resource Optimism ** //
		 full_production_class_name = "";
		 test_class_name_without_package = "";
		 production_class_name_without_package = "";
		 if(cu.getPackage() == null)
			 first_package_qualifier = "";
		 else
	         first_package_qualifier = cu.getPackage().getName().getFullyQualifiedName().split("[.]")[0];
		 
		 if (cu.getPackage()!=null){
		    package_name = cu.getPackage().getName().toString();
		 }		 

		 cu.accept(new ASTVisitor() {
			 // Visit each class
			 @Override
			 public boolean visit(TypeDeclaration class_node) {
				 String class_name = class_node.getName().toString();
				 String full_class_name = package_name + '.' + class_name;
				 // Go inside each class
				 class_node.accept(new ASTVisitor() {
				  	String invoked_method = null;
					@Override
			  		// Visit each method
					public boolean visit(MethodDeclaration method_node){
						List<Integer> line_numbers = new ArrayList<>();
						List<String> retained_statements = new ArrayList<>();
						List<ASTNode> retained_statement_nodes = new ArrayList<>();
						List<String> smells_found = new ArrayList<>();
					    String parent_class = method_node.getParent() instanceof TypeDeclaration ? ((TypeDeclaration)method_node.getParent()).getName().toString(): "";
					    if(parent_class.equals(class_name) && !(method_node.getParent() instanceof AnonymousClassDeclaration)) {
						   // ** Eager Testing and Indirect Testing ** //
						   full_production_class_name = extractProductionClassName(((TypeDeclaration)method_node.getParent()).getName().toString());
					       full_test_class_name = ((TypeDeclaration)method_node.getParent()).getName().toString();
					       test_class_name_without_package = full_test_class_name.substring(full_test_class_name.lastIndexOf(".") + 1).split("[.]")[0];
					       String production_class_name_split[] = test_class_name_without_package.replace("TestCase","").replace("Test", "").split("[.]");
					       production_class_name_without_package = production_class_name_split[production_class_name_split.length - 1];

						   String method_name = method_node.getName().toString();
					       String full_code = method_node.toString();
					       String method_signature = method_node.toString().split("\\{\\s*?\\r?\n")[0] + "{\n";
					       
					       // Go inside each method
					       String actual_class_name = test_case_class_name.contains(".") ? test_case_class_name.substring(test_case_class_name.lastIndexOf(".")+1) : test_case_class_name;
					       String actual_method_name = test_case_method_name.contains("[") ? test_case_method_name.substring(0, test_case_method_name.indexOf("[")) : test_case_method_name;
						   if((actual_class_name+"-"+test_case_method_name).equals(class_name+"-"+method_name) || (actual_class_name+"-"+actual_method_name).equals(class_name+"-"+method_name)) {
						       method_node.accept(new ASTVisitor() {
									public void retain_node(ASTNode node, String tag, String type) {
										int line = cu.getLineNumber(node.getStartPosition());
							            ASTNode statement_node = null;
							            String statement_string = "";

							            if (type.equals("Conditional Test")) {
							            	statement_node = node;
							            	statement_string = statement_node.toString().trim().split("\\{")[0];
							            }
										else {
							            	statement_node = getFullStatement(node);
							            	if (type.equals("Run War"))
								                statement_string = statement_node.toString().replace("\n", "").split("\\{")[0];
							            	else
								            	statement_string = statement_node.toString().trim();
										}
											
							            if (tag.equals("split"))
							            	statement_string = statement_string.split("\\{")[0];
							            else
							            	statement_string = statement_string + tag;
							            
							            line_numbers.add(line);
						                smells_found.add(type);
							            if(!retained_statement_nodes.isEmpty() && retained_statement_nodes.get(retained_statement_nodes.size()-1).equals(statement_node)) {
							            	if (!tag.equals("split"))
							            		if(!retained_statements.get(retained_statements.size()-1).contains(tag))
									            	retained_statements.set(retained_statements.size()-1, retained_statements.get(retained_statements.size()-1)+ tag);
							            }
							            else {
							            	retained_statement_nodes.add(statement_node);
								            retained_statements.add(statement_string);
							            }
									}

									@Override
							        public boolean visit(MethodInvocation node) {
									   // ** Assertion Roulette ** //
						               if (assert_methods.contains(node.getName().toString())) {
						            	   retain_node(node, "", "Assertion Roulette");
						               }
						               
						              // ** Fire and Forget ** //
						              IMethodBinding iMethodBinding = node.resolveMethodBinding();
									  if(iMethodBinding != null){
											ITypeBinding declaring_class = iMethodBinding.getDeclaringClass();
											if(declaring_class != null && declaring_class.getBinaryName() != null){
												String invoked_owner = declaring_class.getBinaryName();
												if (invoked_owner.contains("java.lang.Thread") || invoked_owner.contains("java.util.concurrent") || invoked_owner.contains("java.lang.Runnable"))
												{
													retain_node(node, "", "Fire and Forget");
												}
											}
									   }
									  
									  // ** Mystery Guest ** //
									  if (node.getName() != null) {
											String invoked_name = node.getName().getFullyQualifiedName();
											handle(node, iMethodBinding, invoked_name);
									  }
									  
									  // ** Eager Testing ** //
						              if(iMethodBinding != null){
						                  ITypeBinding declaring_class = iMethodBinding.getDeclaringClass();
						                  if(declaring_class != null){
						                      String invoked_owner = declaring_class.getBinaryName();
						                      if(invoked_owner != null) {
							                      String[] invoked_owner_split = invoked_owner.split("[.]");
							                      if(invoked_owner_split[invoked_owner_split.length - 1].equals(full_production_class_name)) {
							                          String method_name = node.getName().toString();
							                          if(invoked_method == null) {
							                              invoked_method = method_name;
							                          }
							                          else if(!invoked_method.equals(method_name)) {
							                        	  retain_node(node, "//ET", "Eager Testing");
							                          }
							                      }
						                      }
						                  }
						              }
	
						              // ** Indirect Testing ** //
						              if(iMethodBinding != null){
						            	  if (node.resolveMethodBinding().getDeclaringClass()!=null){
						            		 String invoked_owner = node.resolveMethodBinding().getDeclaringClass().getBinaryName();
							                 if(invoked_owner != null) {
								                  String[] invoked_owner_split = invoked_owner.split("[.]");
								                  if (invoked_owner_split[0].equals(first_package_qualifier)
							                          && !invoked_owner_split[invoked_owner_split.length - 1].equals(production_class_name_without_package)
							                          && !invoked_owner_split[invoked_owner_split.length - 1].equals(test_class_name_without_package)
							                      ){
								                	  retain_node(node, "//IT", "Indirect Testing");
							                      }
							                 }
						            	  }
						              }
						              return super.visit(node);
							       }
							        
							       // ** Conditional Logic ** //
							       @Override
							       public boolean visit(IfStatement node) {
							    	  if (node.getThenStatement() instanceof Block)
									  	 retain_node(node, "{", "Conditional Test");
									  else
									  	 retain_node(node, "", "Conditional Test");
							          return super.visit(node);
							       }
							  	   @Override
							       public void endVisit(IfStatement node) {
							  		 if (node.getThenStatement() instanceof Block) 
								  		retained_statements.set(retained_statements.size()-1, retained_statements.get(retained_statements.size()-1)+ "\n}");
							  		 super.endVisit(node);
							  	   }
							  	   
							  	   // ** Mystery Guest ** //
							       private void handle(Expression node, IMethodBinding iMethodBinding, String invoked_name) {
										if (iMethodBinding == null || invoked_name == null)
											return;
										ITypeBinding declaring_class = iMethodBinding.getDeclaringClass();
										if (declaring_class != null && declaring_class.getBinaryName() != null) {
											String invoked_owner = declaring_class.getBinaryName();
											if(invoked_name == null || invoked_owner == null){
												return;
											}
											if (invoked_name.equals("<init>") && invoked_owner.contains("java.io.File")) {
												retain_node(node, "split", "Mystery Guest");  // - Init
											} else if (invoked_name.contains("getConnection") && (invoked_owner.contains("java.sql") || invoked_owner.contains("javax.sql") || invoked_owner.contains("javax.persistence"))) {
												retain_node(node, "split", "Mystery Guest");  // - Connection/SQL
											} else if (invoked_name.contains("socket") && (invoked_owner.contains("java.net") || invoked_owner.contains("javax.net"))) {
												retain_node(node, "split", "Mystery Guest");  // - Socket/Net
											}
											return;
										}
									}
							        
									@Override
									public boolean visit(ClassInstanceCreation node) {
										if (node != null) {
											// ** Mystery Guest ** //
											IMethodBinding iMethodBinding = node.resolveConstructorBinding();
											handle(node, iMethodBinding, "<init>");
											
											// ** Resource Optimism ** //
											if (iMethodBinding != null){
												ITypeBinding declaring_class = iMethodBinding.getDeclaringClass();
												if(declaring_class!= null){
													String invoked_owner = declaring_class.getBinaryName();
													if (invoked_owner != null && invoked_owner.equals("java.io.File") && node.arguments().size() == 1) {
														Object arg = node.arguments().get(0);
														if(arg != null) {
															if (setup_files_and_paths.isEmpty()) {
																retain_node(node, "//RO", "Resource Optimism");  // - Empty Paths
															}
															else {
																for (String f : setup_files_and_paths) {
																	if (!arg.toString().contains(f)
																		|| !arg.toString().contains(f + ".getPath();")
																		|| !arg.toString().contains(f + ".getAbsolutePath();")
																		|| !arg.toString().contains(f + ".getCanonicalPath();")
																	   )
																	{
																		retain_node(node, "//RO", "Resource Optimism");  // - With Paths
																	}
																}
															}
														}
													}
												}
											}
										}
										return true;
									}
									
									// ** Run War ** //		                
						            @Override
						            public boolean visit(SimpleName node) {
						                if(static_non_final_variables.contains(node.getIdentifier())) {
							                retain_node(node, "//RW", "Run War");

						                    return false;
						                }
						                return super.visit(node);
						            }
					       });
						   
					       // Save test cases
	                       try {
	                    		   String preprocessed_code = retainLinesOfCodeMatchingTestSmells(line_numbers, retained_statements, method_signature);
	                    		   visited = true;
	                    		   output_file.write(
		 	 						   full_test_case_name + "," + 
		 	 						   project_name + "," + 
		 	 						   version + "," + 
					    		       package_name + ","+
					    		       test_case_class_name + "," + 
					    		       test_case_method_name + "," + 
								       flaky + "," + 
								       ("\"" + smells_found + "\",") +
						               ("\"" + (full_code).replaceAll("\"", "\"\"") + "\",") +
						               ("\"" + (preprocessed_code).replaceAll("\"", "\"\"") + "\"") +
						               "\n"
		  			               );
	                    		   
		           				   FileWriter full_code_file = new FileWriter(test_cases_full_code_path + full_test_case_name + ".java");
		           				   full_code_file.write(full_code);
		           				   full_code_file.close();

		           				   FileWriter preprocessed_code_file = new FileWriter(test_cases_preprocessed_code_path + full_test_case_name + ".java");
		        				   preprocessed_code_file.write(preprocessed_code);
		        				   preprocessed_code_file.close();
}
	                       catch (IOException e){}
					  }
				  }
			      return false;
				}
			   });
			   return false;
			   }
	  	    });
   }
					    				   
	public String getFileContent(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder fileData = new StringBuilder();
		  
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();
		return fileData.toString();
	}
	
	public String retainLinesOfCodeMatchingTestSmells(List<Integer> line_numbers, List<String> retained_statements, String method_signature) {
		String reduced_code = "";
		reduced_code = method_signature;
		for (String stmt : retained_statements)
			reduced_code += stmt + "\r\n";
		reduced_code += "\n}";
		reduced_code = reduced_code.replaceAll("\n(?:[\\t ]*(?:\\r?\\n|\\r))+", "\n");
		reduced_code = reduced_code.replaceAll(";\\s*\\n\\s*\\{\\s*\\}", ";\n").replaceAll("\\}\\s*\\n\\s*\\{\\s*\\}", "}\n").replaceAll("\\n\\s*\\n+", "\n");
		if(!reduced_code.trim().endsWith("}"))
			reduced_code += "}";
		return reduced_code;
	}
		
	public void retain(String input_data_file, String output_path, String test_cases_class_files_path, String test_cases_full_code_path, String test_cases_preprocessed_code_path) throws IOException {
		FileWriter output_file = new FileWriter(output_path);
		new File(test_cases_full_code_path).mkdirs();
		new File(test_cases_preprocessed_code_path).mkdirs();
		output_file.write("test_case_name,project,version,package_name,class_name,method_name,flaky,smells_found,full_code,preprocessed_code\n");
		File file = new File(input_data_file);
		String csv = getFileContent(file);

		List<String> visited_test_cases = new ArrayList<String>();

		int i = 0;
		for (String line : csv.split("\n")) {
			i++;
			if (i == 1)
				continue;
			
			System.out.println(i-1);
			String[] cols = line.split(",");
			String project_name = cols[0];
			String class_name = cols[1];
			String method_name = cols[2];
			String flaky = cols[3];
			String version = cols[4].replace("\r", "");
			
			String test_case_full_code_file_name = test_cases_class_files_path + (version.equals("") ? "" : version + ".") + class_name + "-" + method_name + ".java";
			File testFile = new File (test_case_full_code_file_name);
			String full_test_case_name = testFile.getName().substring(0,testFile.getName().length()-5);

			if (visited_test_cases.contains(full_test_case_name))
				continue;
			visited_test_cases.add(full_test_case_name);

			String source_code = "";
			
			try {
				 source_code = getFileContent(testFile);
		    }
			catch (FileNotFoundException ex) {
				continue;
		    }
			
			detectRetainTestSmells(source_code, output_file, full_test_case_name, test_cases_full_code_path, test_cases_preprocessed_code_path, class_name, method_name, project_name, flaky, version);

			output_file.flush();
		}
		output_file.close();
	}
	public static void main(String[] args) throws IOException {
		String input_data_file = args[0];
		String test_cases_class_files_path = args[1];
		String test_cases_full_code_path = args[2];
		String test_cases_preprocessed_code_path = args[3];
		String output_file_path = args[4];

		FlakifySmellsDetector smell_detector = new FlakifySmellsDetector();
		smell_detector.retain(input_data_file, output_file_path, test_cases_class_files_path, test_cases_full_code_path, test_cases_preprocessed_code_path);
    }
}