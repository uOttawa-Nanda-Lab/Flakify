@Test public void runDirectoryRecursive() throws Exception {
  this.cli.run("");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
