@Test public void testCopyrightStripperProcessor() throws Exception {
  final ResourcePreProcessor decoratedProcessor=new CssMinProcessor();
  final ResourcePreProcessor processor=CopyrightKeeperProcessorDecorator.decorate(decoratedProcessor);
  final URL url=ResourcePreProcessor.class.getResource("copyright");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expected");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"css",processor);
}
