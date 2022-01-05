@Test public void dataJpaDependencies() throws Exception {
  this.cli.run("data-jpa.groovy");
  assertThat(this.cli.getOutput(),containsString("Hello World"));
}
