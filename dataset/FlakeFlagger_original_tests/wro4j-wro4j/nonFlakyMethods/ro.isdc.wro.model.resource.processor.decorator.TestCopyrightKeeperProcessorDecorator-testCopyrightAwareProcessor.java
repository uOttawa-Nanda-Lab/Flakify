@Test public void testCopyrightAwareProcessor() throws Exception {
  final ResourcePreProcessor decoratedProcessor=new ResourcePreProcessor(){
    public void process(    final Resource resource,    final Reader reader,    final Writer writer) throws IOException {
      IOUtils.copy(reader,writer);
    }
  }
;
  final ResourcePreProcessor processor=CopyrightKeeperProcessorDecorator.decorate(decoratedProcessor);
  final URL url=ResourcePreProcessor.class.getResource("copyright");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expectedCopyrightAware");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"css",processor);
}
