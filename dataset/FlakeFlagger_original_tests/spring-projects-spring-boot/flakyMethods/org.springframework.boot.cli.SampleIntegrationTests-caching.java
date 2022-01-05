@Test public void caching() throws Exception {
  this.cli.run("caching.groovy");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
