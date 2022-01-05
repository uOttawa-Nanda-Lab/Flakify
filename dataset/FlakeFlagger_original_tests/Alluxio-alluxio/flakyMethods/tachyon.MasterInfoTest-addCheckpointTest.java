@Test public void addCheckpointTest() throws FileDoesNotExistException, SuspectedFileSizeException, FileAlreadyExistException, InvalidPathException, BlockInfoException, FileNotFoundException, TachyonException {
  int fileId=mMasterInfo.createFile("/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  ClientFileInfo fileInfo=mMasterInfo.getClientFileInfo("/testFile");
  Assert.assertEquals("",fileInfo.getCheckpointPath());
  mMasterInfo.addCheckpoint(-1,fileId,1,"/testPath");
  fileInfo=mMasterInfo.getClientFileInfo("/testFile");
  Assert.assertEquals("/testPath",fileInfo.getCheckpointPath());
  mMasterInfo.addCheckpoint(-1,fileId,1,"/testPath");
  fileInfo=mMasterInfo.getClientFileInfo("/testFile");
  Assert.assertEquals("/testPath",fileInfo.getCheckpointPath());
}
