@Test public void mkdirExistingTest() throws IOException {
  mFsShell.mkdir(new String[]{"mkdir","/testFile1"});
  mFsShell.mkdir(new String[]{"mkdir","/testFile1"});
}
