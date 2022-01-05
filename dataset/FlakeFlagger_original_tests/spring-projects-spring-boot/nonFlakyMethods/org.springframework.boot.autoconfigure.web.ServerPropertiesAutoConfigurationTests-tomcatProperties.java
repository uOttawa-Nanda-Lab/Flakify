@Test public void tomcatProperties() throws Exception {
  containerFactory=mock(TomcatEmbeddedServletContainerFactory.class);
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"server.tomcat.basedir:target/foo","server.port:9000");
  this.context.refresh();
  ServerProperties server=this.context.getBean(ServerProperties.class);
  assertNotNull(server);
  assertEquals(new File("target/foo"),server.getTomcat().getBasedir());
  verify(containerFactory).setPort(9000);
}
