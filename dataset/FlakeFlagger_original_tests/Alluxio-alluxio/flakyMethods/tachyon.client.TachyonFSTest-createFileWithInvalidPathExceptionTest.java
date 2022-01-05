@Test(expected=IOException.class) public void createFileWithInvalidPathExceptionTest() throws IOException {
  mTfs.createFile("root/testFile1");
}
