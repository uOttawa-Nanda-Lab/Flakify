@Test(expected=NoSuchBeanDefinitionException.class) public void testEndpointMBeanExporterIsNotInstalled(){
  MockEnvironment environment=new MockEnvironment();
  environment.setProperty("endpoints.jmx.enabled","false");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(environment);
  this.context.register(JmxAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointMBeanExportAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(EndpointMBeanExporter.class);
  fail();
}
