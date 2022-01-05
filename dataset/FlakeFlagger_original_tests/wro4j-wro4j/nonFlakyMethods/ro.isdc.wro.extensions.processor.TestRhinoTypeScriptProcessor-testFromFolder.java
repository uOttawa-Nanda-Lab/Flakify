@Test public void testFromFolder() throws Exception {
  final URL url=getClass().getResource("typescript");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expectedRhino");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"js",victim);
}
