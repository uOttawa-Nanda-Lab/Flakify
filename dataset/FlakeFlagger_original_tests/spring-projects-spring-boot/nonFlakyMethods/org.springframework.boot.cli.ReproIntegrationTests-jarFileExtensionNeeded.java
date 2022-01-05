@Test public void jarFileExtensionNeeded() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("is not a JAR file");
  this.cli.jar("secure.groovy","crsh.groovy");
}
