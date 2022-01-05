@Test public void createFileTest() throws Exception {
  int fileId=mTfs.createFile("/root/testFile1");
  Assert.assertEquals(3,fileId);
  fileId=mTfs.createFile("/root/testFile2");
  Assert.assertEquals(4,fileId);
  fileId=mTfs.createFile("/root/testFile3");
  Assert.assertEquals(5,fileId);
}
