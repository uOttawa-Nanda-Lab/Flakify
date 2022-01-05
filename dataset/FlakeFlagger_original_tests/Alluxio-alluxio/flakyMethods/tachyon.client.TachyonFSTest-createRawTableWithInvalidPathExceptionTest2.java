@Test(expected=IOException.class) public void createRawTableWithInvalidPathExceptionTest2() throws IOException {
  mTfs.createRawTable("/tab les/table1",20);
}
