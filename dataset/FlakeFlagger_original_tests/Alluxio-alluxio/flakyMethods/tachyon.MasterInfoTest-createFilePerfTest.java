@Test public void createFilePerfTest() throws FileAlreadyExistException, InvalidPathException, FileDoesNotExistException, TachyonException {
  for (int k=0; k < 200; k++) {
    mMasterInfo.mkdir("/testFile" + Constants.PATH_SEPARATOR + MasterInfo.COL+ k+ "/"+ 0);
  }
  for (int k=0; k < 200; k++) {
    mMasterInfo.getClientFileInfo("/testFile" + Constants.PATH_SEPARATOR + MasterInfo.COL+ k+ "/"+ 0);
  }
}
