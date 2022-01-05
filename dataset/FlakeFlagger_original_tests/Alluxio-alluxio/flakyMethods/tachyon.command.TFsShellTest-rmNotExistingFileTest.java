@Test public void rmNotExistingFileTest() throws IOException {
  Assert.assertEquals(0,mFsShell.rm(new String[]{"rm","/testFile"}));
}
