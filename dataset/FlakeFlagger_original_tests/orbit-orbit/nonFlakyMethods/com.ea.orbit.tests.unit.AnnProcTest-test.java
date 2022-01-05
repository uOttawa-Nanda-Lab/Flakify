@Test public void test() throws URISyntaxException {
  final JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
  assertNotNull("Need a system compiler to do this test",compiler);
  final StandardJavaFileManager fileManager=compiler.getStandardFileManager(null,null,null);
  final String outputDir="target/testing";
  new File(outputDir).mkdirs();
  final String targetPath=outputDir + "/com/ea/orbit/tests/unit/";
  final List<SimpleJavaFileObject> srcFiles=new ArrayList<>();
  final String[] outputList={targetPath + "SomeActorFactory.java",outputDir + "/META-INF/orbit/actors/interfaces/com.ea.orbit.tests.unit.ISomeActor",outputDir + "/META-INF/orbit/actors/classes/com.ea.orbit.tests.unit.SomeActor"};
  for (  final String fName : outputList) {
    File targetFile=new File(targetPath + fName + ".java");
    targetFile.delete();
  }
  final List<String> files=Arrays.asList("ISomeActor","SomeActor");
  for (  final String fName : files) {
    final File targetFile=new File(targetPath + fName + ".javadoc.properties");
    targetFile.delete();
    assertFalse(targetFile.exists());
    assertNotNull(fName,getClass().getResource(fName + ".java"));
    srcFiles.add(new SimpleJavaFileObject(getClass().getResource(fName + ".java").toURI(),JavaFileObject.Kind.SOURCE){
      @Override public InputStream openInputStream() throws IOException {
        return uri.toURL().openStream();
      }
      @Override public CharSequence getCharContent(      final boolean ignoreEncodingErrors) throws IOException {
        return IOUtils.toString(openInputStream());
      }
    }
);
  }
  assertTrue(compiler.getTask(null,fileManager,null,Arrays.asList("-d",outputDir),null,srcFiles).call());
  for (  final String fName : outputList) {
    File targetFile;
    targetFile=new File(fName);
    assertTrue(targetFile + " should exist",targetFile.exists());
  }
}
