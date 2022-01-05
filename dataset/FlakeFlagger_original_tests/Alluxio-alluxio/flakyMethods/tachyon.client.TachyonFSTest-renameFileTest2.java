@Test public void renameFileTest2() throws IOException {
  mTfs.createFile("/root/testFile1");
  Assert.assertTrue(mTfs.rename("/root/testFile1","/root/testFile1"));
}
