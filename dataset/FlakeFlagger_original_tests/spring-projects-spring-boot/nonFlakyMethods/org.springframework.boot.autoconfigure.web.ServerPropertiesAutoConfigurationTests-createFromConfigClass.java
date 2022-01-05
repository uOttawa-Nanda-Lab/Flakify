@Test public void createFromConfigClass() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"server.port:9000");
  this.context.refresh();
  ServerProperties server=this.context.getBean(ServerProperties.class);
  assertNotNull(server);
  assertEquals(9000,server.getPort().intValue());
  verify(containerFactory).setPort(9000);
}
