@Test public void fileExists() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.equals("File must exist");
  new RandomAccessDataFile(new File("/does/not/exist"));
}
