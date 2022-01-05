@Test public void tooManyEmbeddedServletContainerFactories() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  this.context.registerBeanDefinition("embeddedServletContainerFactory2",new RootBeanDefinition(MockEmbeddedServletContainerFactory.class));
  this.thrown.expect(ApplicationContextException.class);
  this.thrown.expectMessage("Unable to start EmbeddedWebApplicationContext due to " + "multiple EmbeddedServletContainerFactory beans");
  this.context.refresh();
}
