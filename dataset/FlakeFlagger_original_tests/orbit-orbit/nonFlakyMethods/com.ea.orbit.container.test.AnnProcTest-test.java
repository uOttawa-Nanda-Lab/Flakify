@Test public void test() throws URISyntaxException, IOException {
  JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
  assertNotNull("Need a system compiler to do this test",compiler);
  StandardJavaFileManager fileManager=compiler.getStandardFileManager(null,null,null);
  final String outputDir="target/testing/classes";
  new File(outputDir).mkdirs();
  final String sourceDir="target/testing/src";
  new File(sourceDir + "/annotation").mkdirs();
  new File(sourceDir + "2/annotation").mkdirs();
  copyResources("/annotation/AAA.java",sourceDir + "/annotation/AAA.java");
  copyResources("/annotation/SomeModule.java",sourceDir + "/annotation/SomeModule.java");
  final String targetPath=outputDir + "/annotation";
  final String[] outputList={outputDir + "/annotation/AAA.class",outputDir + "/annotation/SomeModule.class",outputDir + "/annotation/SomeModule.moduleClasses"};
  for (  final String fName : outputList) {
    File targetFile=new File(targetPath + fName);
    targetFile.delete();
  }
  List<SimpleJavaFileObject> srcFiles=new ArrayList<>();
  for (  final String fName : Arrays.asList("SomeModule","AAA")) {
    assertNotNull(fName,getClass().getResource("/annotation/" + fName + ".java"));
    srcFiles.add(new JavaFileObject(sourceDir + "/annotation/" + fName+ ".java"));
  }
  assertTrue(compiler.getTask(null,fileManager,null,Arrays.asList("-d",outputDir),null,srcFiles).call());
  for (  final String fName : outputList) {
    File targetFile;
    targetFile=new File(fName);
    assertTrue(targetFile + " should exist",targetFile.exists());
  }
  Set<String> lines=readLines(outputDir + "/annotation/SomeModule.moduleClasses");
  assertTrue(lines.contains("annotation.AAA"));
  assertTrue(lines.contains("annotation.SomeModule"));
  assertEquals(2,lines.size());
  srcFiles=Arrays.asList(new JavaFileObject(sourceDir + "/annotation/SomeModule.java"));
  assertTrue(compiler.getTask(null,fileManager,null,Arrays.asList("-d",outputDir),null,srcFiles).call());
  lines=readLines(outputDir + "/annotation/SomeModule.moduleClasses");
  assertEquals(2,lines.size());
  assertTrue(lines.contains("annotation.SomeModule"));
  assertTrue(lines.contains("annotation.AAA"));
  assertTrue(new File(outputDir + "/annotation/SomeModule.moduleClasses").delete());
  srcFiles=Arrays.asList(new JavaFileObject(sourceDir + "/annotation/AAA.java"));
  assertTrue(compiler.getTask(null,fileManager,null,Arrays.asList("-d",outputDir),null,srcFiles).call());
  lines=readLines(outputDir + "/annotation/SomeModule.moduleClasses");
  assertEquals(2,lines.size());
  assertTrue(lines.contains("annotation.SomeModule"));
  assertTrue(lines.contains("annotation.AAA"));
  assertTrue(new File(outputDir + "/annotation/AAA.class").delete());
  compiler=ToolProvider.getSystemJavaCompiler();
  fileManager=compiler.getStandardFileManager(null,null,null);
  copyResources("/annotation/SomeModule.java",sourceDir + "2/annotation/SomeModule.java");
  srcFiles=Arrays.asList(new JavaFileObject(sourceDir + "2/annotation/SomeModule.java"));
  assertTrue(compiler.getTask(null,fileManager,null,Arrays.asList("-d",outputDir),null,srcFiles).call());
  lines=readLines(outputDir + "/annotation/SomeModule.moduleClasses");
  assertTrue(lines.contains("annotation.SomeModule"));
  assertFalse(lines.contains("annotation.AAA"));
  assertEquals(1,lines.size());
}
