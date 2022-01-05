@Test public void webSample() throws Exception {
  this.cli.run("web.groovy");
  assertEquals("World!",this.cli.getHttpOutput());
}
