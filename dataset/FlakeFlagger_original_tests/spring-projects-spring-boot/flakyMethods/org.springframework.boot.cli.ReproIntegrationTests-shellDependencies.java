@Test public void shellDependencies() throws Exception {
  this.cli.run("crsh.groovy");
  assertThat(this.cli.getHttpOutput(),containsString("{\"message\":\"Hello World\"}"));
}
