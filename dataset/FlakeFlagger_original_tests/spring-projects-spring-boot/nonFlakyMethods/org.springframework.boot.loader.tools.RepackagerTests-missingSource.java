@Test public void missingSource() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  new Repackager(new File("missing"));
}
