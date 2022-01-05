@Test public void fileExistsWithConcurrentReads() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.equals("File must exist");
  new RandomAccessDataFile(new File("/does/not/exist"),1);
}
