@Test(expected=TableColumnException.class) public void negativeColumnTest() throws InvalidPathException, FileAlreadyExistException, TableColumnException, TachyonException {
  mMasterInfo.createRawTable("/testTable",-1,(ByteBuffer)null);
}
