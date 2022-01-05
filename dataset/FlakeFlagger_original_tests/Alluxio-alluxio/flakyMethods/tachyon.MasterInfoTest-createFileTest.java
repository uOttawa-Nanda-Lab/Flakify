@Test public void createFileTest() throws InvalidPathException, FileAlreadyExistException, FileDoesNotExistException, BlockInfoException, TachyonException {
  mMasterInfo.createFile("/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  Assert.assertFalse(mMasterInfo.getClientFileInfo("/testFile").isFolder());
}
