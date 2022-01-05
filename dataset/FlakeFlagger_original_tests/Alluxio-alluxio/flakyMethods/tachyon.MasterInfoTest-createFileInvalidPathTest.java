@Test(expected=InvalidPathException.class) public void createFileInvalidPathTest() throws InvalidPathException, FileAlreadyExistException, BlockInfoException, TachyonException {
  mMasterInfo.createFile("testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
}
