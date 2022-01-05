/** 
 * Test that processing invalid less css produces exceptions
 */
@Test public void shouldFailWhenInvalidLessCssIsProcessed() throws Exception {
  final ResourcePreProcessor processor=new Less4jProcessor();
  final URL url=getClass().getResource("lesscss");
  final File testFolder=new File(url.getFile(),"invalid");
  WroTestUtils.forEachFileInFolder(testFolder,new Function<File,Void>(){
    @Override public Void apply(    final File input) throws Exception {
      try {
        processor.process(Resource.create(input.getPath(),ResourceType.CSS),new FileReader(input),new StringWriter());
        Assert.fail("Expected to fail, but didn't");
      }
 catch (      final Exception e) {
      }
      return null;
    }
  }
);
}
