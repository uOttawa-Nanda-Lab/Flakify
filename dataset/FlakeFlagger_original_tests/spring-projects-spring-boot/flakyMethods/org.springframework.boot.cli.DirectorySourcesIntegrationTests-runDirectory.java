@Test public void runDirectory() throws Exception {
  this.cli.run("code");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
