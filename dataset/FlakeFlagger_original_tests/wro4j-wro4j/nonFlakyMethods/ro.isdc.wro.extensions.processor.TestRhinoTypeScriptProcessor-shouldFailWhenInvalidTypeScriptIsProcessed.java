/** 
 * Test that processing invalid less css produces exceptions
 */
@Test public void shouldFailWhenInvalidTypeScriptIsProcessed() throws Exception {
  final URL url=getClass().getResource("typescript");
  final File testFolder=new File(url.getFile(),"invalid");
  final ResourcePreProcessor decorated=new ExceptionHandlingProcessorDecorator(victim);
  WroTestUtils.createInjector().inject(decorated);
  WroTestUtils.forEachFileInFolder(testFolder,new Function<File,Void>(){
    @Override public Void apply(    final File input) throws Exception {
      try {
        decorated.process(Resource.create(input.getPath(),ResourceType.JS),new FileReader(input),new StringWriter());
        Assert.fail("Expected to fail, but didn't");
      }
 catch (      final WroRuntimeException e) {
      }
      return null;
    }
  }
);
}
