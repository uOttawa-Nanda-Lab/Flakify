@Test public void missingEmbeddedServletContainerFactory() throws Exception {
  this.thrown.expect(ApplicationContextException.class);
  this.thrown.expectMessage("Unable to start EmbeddedWebApplicationContext due to " + "missing EmbeddedServletContainerFactory bean");
  this.context.refresh();
}
