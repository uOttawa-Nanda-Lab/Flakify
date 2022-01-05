@Test public void nullSource() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  new Repackager(null);
}
