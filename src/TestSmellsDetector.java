import java.io.File;
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

public class TestSmellsDetector {
	private static final List<String> assert_methods = Arrays.asList("assertArrayEquals", "assertEquals", "assertFalse", "assertNotNull", "assertNotSame", "assertNull", "assertSame", "assertThat", "assertTrue", "fail");
	List<String> setup_files_and_paths = new ArrayList<String>();
	List<String> static_non_final_variables = new ArrayList<>();
	HashMap<String, String> flakeflagger_test_cases_projects = new HashMap<String, String>();
	HashMap<String, String> flakeflagger_test_cases_labels = new HashMap<String, String>();

	String data_path = "../dataset/";
	String tests_cases_full_code_path = data_path + "test_cases_full_code/";
	String flakeflagger_data_file = data_path + "FlakeFlagger_filtered_dataset.csv";
	String test_cases_preprocessed_code_path = data_path + "test_cases_preprocessed_code/";
	String output_path = data_path + "Flakify_dataset.csv";

	String full_production_class_name = "";
	String full_test_class_name = "";
	String production_class_name_without_package = "";
	String test_class_name_without_package = "";
	String first_package_qualifier = "";
	String package_name = "";
	String flakeflagger_test_case_file_name = "";

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
			   if (method_node.getName().toString().equals("setUp") || String.valueOf(method_node.modifiers()).contains("@Before")) {
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
	
	public void detectRetainTestSmells(String code, FileWriter output_file, String full_test_case_name) throws IOException  {  
		 Document document = new Document(code);
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
		    package_name=cu.getPackage().getName().toString();
		 }		 
         
		 cu.accept(new ASTVisitor() {
		  	 String invoked_method = null;
	  		
			 // Visit each class
			 @Override
			 public boolean visit(TypeDeclaration class_node) {
				 // Go inside each class
				 class_node.accept(new ASTVisitor() {
					@Override
			  		// Visit each method
					public boolean visit(MethodDeclaration method_node){
						List<Integer> line_numbers = new ArrayList<>();
						List<String> retained_lines_of_code = new ArrayList<>();
						List<String> smells_found = new ArrayList<>();
						
						if(!(method_node.getParent() instanceof AnonymousClassDeclaration)) {
						    // ** Eager Testing and Indirect Testing ** //
						    full_production_class_name = extractProductionClassName(((TypeDeclaration)method_node.getParent()).getName().toString());
					        full_test_class_name = ((TypeDeclaration)method_node.getParent()).getName().toString();
					        test_class_name_without_package = full_test_class_name.substring(full_test_class_name.lastIndexOf(".") + 1).split("[.]")[0];
					        String production_class_name_split[] = test_class_name_without_package.replace("TestCase","").replace("Test", "").split("[.]");
					        production_class_name_without_package = production_class_name_split[production_class_name_split.length - 1];
						
						   String test_method_name = method_node.getName().toString();
					       String full_code = method_node.toString();
					       String method_signature = method_node.toString().split("\\{\s*?\\r?\n")[0] + "{\n";
					       
					       // Go inside each method 
					       if(!(test_method_name.equals("setUP") || String.valueOf(method_node.modifiers()).contains("@Before"))) {
						       method_node.accept(new ASTVisitor() {
								    @Override
							        public boolean visit(MethodInvocation node) {
									   // ** Assertion Roulette ** //
						               if (assert_methods.contains(node.getName().toString())) {
						                  int line = cu.getLineNumber(node.getStartPosition());
						                  line_numbers.add(line);
						                  ASTNode parent_node = getFullStatement(node);
					                      retained_lines_of_code.add(parent_node.toString().trim().split("\\{")[0]);	
					                      smells_found.add("Assertion Roulette");
						               }
						               
						              // ** Fire and Forget ** //
						              IMethodBinding iMethodBinding = node.resolveMethodBinding();
									  if(iMethodBinding != null){
											ITypeBinding declaring_class = iMethodBinding.getDeclaringClass();
											if(declaring_class != null && declaring_class.getBinaryName() != null){
												String invoked_owner = declaring_class.getBinaryName();
												if (invoked_owner.contains("java.lang.Thread") || invoked_owner.contains("java.util.concurrent") || invoked_owner.contains("java.lang.Runnable"))
												{
												    int line = cu.getLineNumber(node.getStartPosition());
							                        line_numbers.add(line);
							                        if (node.getParent() instanceof MethodDeclaration)
							                        	retained_lines_of_code.add(node.toString());
							                        else
							                        	retained_lines_of_code.add(node.getParent().toString());
							                        smells_found.add("Fire and Forget");
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
							  						    int line = cu.getLineNumber(node.getStartPosition());
								                        line_numbers.add(line);
								                        if (node.getParent() instanceof MethodDeclaration)
								                        	retained_lines_of_code.add(node.toString().trim() + ";" + "//ET");
								                        else
								                        	retained_lines_of_code.add(node.getParent().toString().trim() + "//ET");
								                        smells_found.add("Eager Testing");
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
												      int line = cu.getLineNumber(node.getStartPosition());
							                          line_numbers.add(line);
								                        if (node.getParent() instanceof MethodDeclaration)
								                        	retained_lines_of_code.add(node.toString().trim() + ";" + "//IT");
								                        else
								                        	retained_lines_of_code.add(node.getParent().toString().trim() + "//IT");
							                    	  smells_found.add("Indirect Testing");
							                      }
							                 }
						            	  }
						              }
						              return super.visit(node);
							       }
							        
							       // ** Conditional Logic ** //
							       @Override
							       public boolean visit(IfStatement node) {
							          int line = cu.getLineNumber(node.getStartPosition());
							          line_numbers.add(line);
								  	  if (node.getThenStatement() instanceof Block)
								          retained_lines_of_code.add(node.toString().split("\\{")[0] + "{");
								  	  else
								          retained_lines_of_code.add(node.toString().split("\\{")[0] + "");
								  	  smells_found.add("Conditional Test");
							          return super.visit(node);
							       }
							  	   @Override
							       public void endVisit(IfStatement node) {
							  		 if (node.getThenStatement() instanceof Block) 
								  		retained_lines_of_code.set(retained_lines_of_code.size()-1, retained_lines_of_code.get(retained_lines_of_code.size()-1)+ "\n}");
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
												int line = cu.getLineNumber(node.getStartPosition());
												line_numbers.add(line);
												ASTNode parent_node = getFullStatement(node.getParent());
						                        retained_lines_of_code.add(parent_node.toString().trim().split("\\{")[0]);
						                        smells_found.add("Mystery Guest - Init");
											} else if (invoked_name.contains("getConnection") && (invoked_owner.contains("java.sql") || invoked_owner.contains("javax.sql") || invoked_owner.contains("javax.persistence"))) {
												int line = cu.getLineNumber(node.getStartPosition());
						                        line_numbers.add(line);
						                        ASTNode parent_node = getFullStatement(node.getParent());
						                        retained_lines_of_code.add(parent_node.toString().trim().split("\\{")[0]);
						                        smells_found.add("Mystery Guest - Connection/SQL");
											} else if (invoked_name.contains("socket") && (invoked_owner.contains("java.net") || invoked_owner.contains("javax.net"))) {
												int line = cu.getLineNumber(node.getStartPosition());
												line_numbers.add(line);
												ASTNode parent_node = getFullStatement(node.getParent());
						                        retained_lines_of_code.add(parent_node.toString().trim().split("\\{")[0]);
						                        smells_found.add("Mystery Guest - Socket/Net");
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
												                  int line = cu.getLineNumber(node.getStartPosition());
												                  line_numbers.add(line);
											                      retained_lines_of_code.add(node.toString() + ";" + "//RO");
											                      smells_found.add("Resource Optimism - Empty Paths");
															}
															else {
																for (String f : setup_files_and_paths) {
																	if (!arg.toString().contains(f)
																		|| !arg.toString().contains(f + ".getPath();")
																		|| !arg.toString().contains(f + ".getAbsolutePath();")
																		|| !arg.toString().contains(f + ".getCanonicalPath();")
																	   )
																	{
														                int line = cu.getLineNumber(node.getStartPosition());
														                line_numbers.add(line);
													                    retained_lines_of_code.add(node.toString() + ";" + "//RO");
													                    smells_found.add("Resource optimism - With Paths");
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
							                int line = cu.getLineNumber(node.getStartPosition());
							                ASTNode parent_node = getFullStatement(node);
					                		line_numbers.add(line);
					                        retained_lines_of_code.add(parent_node.toString().replace("\n", "").split("\\{")[0] + "//RW");
						                    return false;
						                }
						                return super.visit(node);
						            }
					       });
						   
					       // Save test cases
	                       try {
	                    	   if (flakeflagger_test_cases_labels.keySet().contains(full_test_case_name)) {
	                    		   String preprocessed_code = retainLinesOfCodeMatchingTestSmells(line_numbers, retained_lines_of_code, full_code, method_signature);
	                    		   output_file.write(
		 	 						   full_test_case_name + "," + 
		 		 	 				   flakeflagger_test_cases_projects.get(full_test_case_name) + "," + 
					    		       package_name + ","+
									   full_test_class_name + "," + 
								       test_method_name + "," + 
								       flakeflagger_test_cases_labels.get(full_test_case_name) + "," + 
								       ("\"" + smells_found + "\",") +
						               ("\"" + full_code.replaceAll("\"", "\"\"") + "\",") +
						               ("\"" + preprocessed_code.replaceAll("\"", "\"\"") + "\"\n")
		  			               );
	                    		   
		           				   FileWriter preprocessed_code_file = new FileWriter(test_cases_preprocessed_code_path + full_test_case_name + ".java");
		        				   preprocessed_code_file.write(preprocessed_code);
		        				   preprocessed_code_file.close();
	                    	   }
	                       }
	                       catch (IOException e){}
					  }
				  }
			      return false;
				}
			   });
			   return super.visit(class_node);
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
	
	public ArrayList<Integer> getSortedUniqueLineNumbers(List<Integer> line_numbers){
		Set<Integer> set = new HashSet<Integer>(line_numbers);
		ArrayList<Integer> sorted_line_numbers = new ArrayList<Integer>(set);
		Collections.sort(sorted_line_numbers);
		return sorted_line_numbers;
	}
	
	public String retainLinesOfCodeMatchingTestSmells(List<Integer> line_numbers, List<String> retained_lines_of_code, String original_code, String method_signature) {
		String reduced_code = "";
		ArrayList<Integer> sorted_line_numbers = getSortedUniqueLineNumbers(line_numbers);
		reduced_code = method_signature;
		for (int i = 0; i < sorted_line_numbers.size(); i++)
			reduced_code += retained_lines_of_code.get(line_numbers.indexOf(sorted_line_numbers.get(i))) + "\r\n";
		reduced_code += "\n}";
		reduced_code = reduced_code.replaceAll("\n(?:[\\t ]*(?:\\r?\\n|\\r))+", "\n");
		reduced_code = reduced_code.replaceAll(";\\s*\\n\\s*\\{\\s*\\}", ";\n").replaceAll("\\}\\s*\\n\\s*\\{\\s*\\}", "}\n").replaceAll("\\n\\s*\\n+", "\n");
		if(!reduced_code.trim().endsWith("}"))
			reduced_code += "}";
		return reduced_code;
	}
	
	public void getFlakeFlaggerTestCases() throws IOException {
		File file = new File(flakeflagger_data_file);
		String csv = getFileContent(file);
		int i = 0;
		for (String line : csv.split("\n")) {
			if (i == 0) {
				i++;
				continue;
			}
			String[] cols = line.split(",");
			flakeflagger_test_cases_projects.put(cols[3]+"-"+cols[4], cols[2]);
			flakeflagger_test_cases_labels.put(cols[3]+"-"+cols[4], cols[5]);
		}
	}
	
	public void retain() throws IOException {
		getFlakeFlaggerTestCases();
		FileWriter output_file = new FileWriter(output_path);
		File[] tests_folder = new File(tests_cases_full_code_path).listFiles();
		if(tests_folder != null) {
			output_file.write("test_name,project,package_name,class_name,method_name,flaky,smells_found,full_code,preprocessed_code\n");
			System.out.println("Analyzing test cases:");
			Arrays.sort(tests_folder);
			int i = 0;
			for (File test_file : tests_folder) {
				System.out.println("  - " + ++i);
				String full_test_case_name = test_file.getName().substring(0,test_file.getName().length()-5);
				String source_code = getFileContent(test_file);
				detectRetainTestSmells(source_code, output_file, full_test_case_name);
			}
			output_file.close();
		}
		else
			System.out.println("Test cases directory unavailable!");
	}
	
	public static void main(String[] args) throws IOException {
    	TestSmellsDetector smells = new TestSmellsDetector();
    	smells.retain();
    }
}