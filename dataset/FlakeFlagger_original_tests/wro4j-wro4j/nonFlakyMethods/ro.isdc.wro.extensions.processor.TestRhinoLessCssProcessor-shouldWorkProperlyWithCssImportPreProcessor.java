@Test public void shouldWorkProperlyWithCssImportPreProcessor() throws Exception {
  final ResourcePreProcessor processor=ChainedProcessor.create(new CssImportPreProcessor(),new RhinoLessCssProcessor());
  final URL url=getClass().getResource("lesscss");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expectedUrlRewriting");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"css",processor);
}
