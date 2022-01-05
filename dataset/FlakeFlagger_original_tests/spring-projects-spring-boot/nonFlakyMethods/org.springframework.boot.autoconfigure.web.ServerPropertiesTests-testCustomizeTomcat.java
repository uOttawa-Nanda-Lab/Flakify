@Test public void testCustomizeTomcat() throws Exception {
  ConfigurableEmbeddedServletContainer factory=mock(ConfigurableEmbeddedServletContainer.class);
  this.properties.customize(factory);
  verify(factory,never()).setContextPath("");
}
