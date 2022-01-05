@Test public void deleteFileTest() throws InvalidPathException, FileAlreadyExistException, TachyonException, BlockInfoException {
  int fileId=mMasterInfo.createFile("/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFile"));
  Assert.assertTrue(mMasterInfo.delete(fileId,true));
  Assert.assertEquals(-1,mMasterInfo.getFileId("/testFile"));
}
