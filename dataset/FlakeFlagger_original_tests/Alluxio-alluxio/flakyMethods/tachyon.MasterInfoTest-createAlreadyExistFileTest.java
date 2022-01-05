@Test(expected=FileAlreadyExistException.class) public void createAlreadyExistFileTest() throws InvalidPathException, FileAlreadyExistException, BlockInfoException, TachyonException {
  mMasterInfo.createFile("/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  mMasterInfo.mkdir("/testFile");
}
