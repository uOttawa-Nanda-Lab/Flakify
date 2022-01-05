@Test public void deleteDirectoryWithFilesTest() throws InvalidPathException, FileAlreadyExistException, TachyonException, BlockInfoException {
  Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));
  int fileId=mMasterInfo.createFile("/testFolder/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));
  Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));
  Assert.assertTrue(mMasterInfo.delete(2,true));
  Assert.assertEquals(-1,mMasterInfo.getFileId("/testFolder"));
  Assert.assertEquals(-1,mMasterInfo.getFileId("/testFolder/testFile"));
}
