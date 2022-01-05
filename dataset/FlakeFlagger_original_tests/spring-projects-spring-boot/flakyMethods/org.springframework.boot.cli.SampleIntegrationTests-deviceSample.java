@Test public void deviceSample() throws Exception {
  this.cli.run("device.groovy");
  assertEquals("Hello Normal Device!",this.cli.getHttpOutput());
}
