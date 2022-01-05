@Test public void testFromFolder() throws IOException {
  final URL url=getClass().getResource("packer");
  final File expectedFolder=new File(url.getFile(),"expected");
  WroTestUtils.compareFromDifferentFoldersByExtension(testFolder,expectedFolder,"js",processor);
}
