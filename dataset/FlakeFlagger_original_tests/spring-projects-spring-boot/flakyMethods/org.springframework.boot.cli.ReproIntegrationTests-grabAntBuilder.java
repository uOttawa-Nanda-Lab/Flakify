@Test public void grabAntBuilder() throws Exception {
  this.cli.run("grab-ant-builder.groovy");
  assertThat(this.cli.getHttpOutput(),containsString("{\"message\":\"Hello World\"}"));
}
