@Test(expected=IOException.class) public void createFileWithFileAlreadyExistExceptionTest() throws IOException {
  int fileId=mTfs.createFile("/root/testFile1");
  Assert.assertEquals(3,fileId);
  fileId=mTfs.createFile("/root/testFile1");
}
