@Test public void createRawTableTest() throws InvalidPathException, FileAlreadyExistException, TableColumnException, FileDoesNotExistException, TachyonException {
  mMasterInfo.createRawTable("/testTable",1,(ByteBuffer)null);
  Assert.assertTrue(mMasterInfo.getClientFileInfo("/testTable").isFolder());
}
