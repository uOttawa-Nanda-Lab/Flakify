@Test public void securityDependencies() throws Exception {
  this.cli.run("secure.groovy");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
