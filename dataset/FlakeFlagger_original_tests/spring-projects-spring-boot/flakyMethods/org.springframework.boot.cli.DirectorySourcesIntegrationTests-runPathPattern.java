@Test public void runPathPattern() throws Exception {
  this.cli.run("**/*.groovy");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
