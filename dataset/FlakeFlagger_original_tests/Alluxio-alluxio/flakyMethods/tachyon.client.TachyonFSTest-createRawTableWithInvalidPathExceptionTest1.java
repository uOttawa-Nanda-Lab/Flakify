@Test(expected=IOException.class) public void createRawTableWithInvalidPathExceptionTest1() throws IOException {
  mTfs.createRawTable("tables/table1",20);
}
