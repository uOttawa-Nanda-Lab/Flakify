@Test public void customizeWithContainerFactory() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(CustomContainerConfig.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  containerFactory=this.context.getBean(AbstractEmbeddedServletContainerFactory.class);
  ServerProperties server=this.context.getBean(ServerProperties.class);
  assertNotNull(server);
  assertEquals(3000,containerFactory.getPort());
}
