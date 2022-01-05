@Test(expected=TableColumnException.class) public void tooManyColumnsTest() throws InvalidPathException, FileAlreadyExistException, TableColumnException, TachyonException {
  mMasterInfo.createRawTable("/testTable",Constants.MAX_COLUMNS + 1,(ByteBuffer)null);
}
