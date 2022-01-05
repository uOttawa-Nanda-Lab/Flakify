@Test public void fileNotNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.equals("File must not be null");
  new RandomAccessDataFile(null);
}
