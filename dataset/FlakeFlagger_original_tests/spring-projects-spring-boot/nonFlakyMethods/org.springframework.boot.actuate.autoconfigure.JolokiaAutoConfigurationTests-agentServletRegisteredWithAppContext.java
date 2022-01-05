@Test public void agentServletRegisteredWithAppContext() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"jolokia.config[key1]:value1","jolokia.config[key2]:value2");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,JolokiaAutoConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(JolokiaMvcEndpoint.class).length);
}
