@Test public void directorySource() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  new Repackager(this.temporaryFolder.getRoot());
}
