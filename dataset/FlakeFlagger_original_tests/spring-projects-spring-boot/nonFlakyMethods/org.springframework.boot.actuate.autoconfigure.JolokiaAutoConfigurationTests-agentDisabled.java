@Test public void agentDisabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"endpoints.jolokia.enabled:false");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,JolokiaAutoConfiguration.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(JolokiaMvcEndpoint.class).length);
}
