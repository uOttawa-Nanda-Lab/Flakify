@Test public void customizeTomcatWithCustomizer() throws Exception {
  containerFactory=mock(TomcatEmbeddedServletContainerFactory.class);
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,CustomizeConfig.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  ServerProperties server=this.context.getBean(ServerProperties.class);
  assertNotNull(server);
  verify(containerFactory).setPort(3000);
}
