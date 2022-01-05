@Test(expected=IOException.class) public void createRawTableWithTableColumnExceptionTest3() throws IOException {
  mTfs.createRawTable("/table",-1);
}
