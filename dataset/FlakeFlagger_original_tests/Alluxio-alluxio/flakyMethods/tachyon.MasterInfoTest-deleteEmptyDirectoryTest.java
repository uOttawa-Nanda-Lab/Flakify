@Test public void deleteEmptyDirectoryTest() throws InvalidPathException, FileAlreadyExistException, TachyonException {
  Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));
  Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));
  Assert.assertTrue(mMasterInfo.delete("/testFolder",true));
  Assert.assertEquals(-1,mMasterInfo.getFileId("/testFolder"));
}
