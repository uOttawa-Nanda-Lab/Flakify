@Test public void shouldTestFromFolder() throws Exception {
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expected");
  WroTestUtils.compareFromDifferentFoldersByName(testFolder,expectedFolder,"css","css",processor);
}
