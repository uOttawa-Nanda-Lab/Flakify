@Test public void deleteDirectoryWithFilesTest2() throws InvalidPathException, FileAlreadyExistException, TachyonException, BlockInfoException {
  Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));
  int fileId=mMasterInfo.createFile("/testFolder/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));
  Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));
  Assert.assertFalse(mMasterInfo.delete(2,false));
  Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));
  Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));
}
