@Test public void shouldDetectProperlyCssImportStatements() throws Exception {
  final ResourcePreProcessor processor=ChainedProcessor.create(new LessCssImportPreProcessor(),new Less4jProcessor());
  final URL url=getClass().getResource("lesscss");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expectedLessCssImport");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"css",processor);
}
