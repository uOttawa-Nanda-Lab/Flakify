@Test public void testFromFolder() throws Exception {
  final ResourcePreProcessor processor=new Less4jProcessor();
  final URL url=getClass().getResource("lesscss");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expectedLess4j");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"css",processor);
}
