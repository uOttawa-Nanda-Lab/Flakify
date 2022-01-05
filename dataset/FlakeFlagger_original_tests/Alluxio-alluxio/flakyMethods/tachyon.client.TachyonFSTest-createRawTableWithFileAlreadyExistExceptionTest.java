@Test(expected=IOException.class) public void createRawTableWithFileAlreadyExistExceptionTest() throws IOException {
  mTfs.createRawTable("/table",20);
  mTfs.createRawTable("/table",20);
}
