@Test(expected=IOException.class) public void createRawTableWithTableColumnExceptionTest2() throws IOException {
  mTfs.createRawTable("/table",0);
}
