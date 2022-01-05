@Test public void createDirectoryTest() throws InvalidPathException, FileAlreadyExistException, FileDoesNotExistException, TachyonException {
  mMasterInfo.mkdir("/testFolder");
  ClientFileInfo fileInfo=mMasterInfo.getClientFileInfo("/testFolder");
  Assert.assertTrue(fileInfo.isFolder());
}
