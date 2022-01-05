@Test(expected=IOException.class) public void createRawTableWithTableColumnExceptionTest1() throws IOException {
  mTfs.createRawTable("/table",Constants.MAX_COLUMNS);
}
