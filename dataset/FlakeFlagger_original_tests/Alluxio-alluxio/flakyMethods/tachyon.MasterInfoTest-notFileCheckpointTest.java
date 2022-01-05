@Test(expected=FileNotFoundException.class) public void notFileCheckpointTest() throws FileNotFoundException, SuspectedFileSizeException, FileAlreadyExistException, InvalidPathException, BlockInfoException, TachyonException {
  Assert.assertTrue(mMasterInfo.mkdir("/testFile"));
  mMasterInfo.addCheckpoint(-1,mMasterInfo.getFileId("/testFile"),0,"/testPath");
}
