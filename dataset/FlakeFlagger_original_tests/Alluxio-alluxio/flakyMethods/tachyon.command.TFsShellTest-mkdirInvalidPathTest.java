@Test(expected=IOException.class) public void mkdirInvalidPathTest() throws IOException {
  mFsShell.mkdir(new String[]{"mkdir","/test File Invalid Path"});
}
