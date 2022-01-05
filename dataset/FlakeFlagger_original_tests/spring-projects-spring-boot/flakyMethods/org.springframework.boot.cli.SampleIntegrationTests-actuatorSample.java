@Test public void actuatorSample() throws Exception {
  this.cli.run("actuator.groovy");
  assertEquals("{\"message\":\"Hello World!\"}",this.cli.getHttpOutput());
}
