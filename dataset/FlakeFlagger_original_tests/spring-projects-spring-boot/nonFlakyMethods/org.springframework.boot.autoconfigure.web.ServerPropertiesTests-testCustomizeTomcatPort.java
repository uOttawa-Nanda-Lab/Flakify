@Test public void testCustomizeTomcatPort() throws Exception {
  ConfigurableEmbeddedServletContainer factory=mock(ConfigurableEmbeddedServletContainer.class);
  this.properties.setPort(8080);
  this.properties.customize(factory);
  verify(factory).setPort(8080);
}
