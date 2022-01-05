@Test public void testEndpointMBeanExporterWithProperties() throws IntrospectionException, InstanceNotFoundException, MalformedObjectNameException, ReflectionException {
  MockEnvironment environment=new MockEnvironment();
  environment.setProperty("endpoints.jmx.domain","test-domain");
  environment.setProperty("endpoints.jmx.unique_names","true");
  environment.setProperty("endpoints.jmx.static_names","key1=value1, key2=value2");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(environment);
  this.context.register(JmxAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointMBeanExportAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(EndpointMBeanExporter.class);
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(ObjectNameManager.getInstance(getObjectName("test-domain","healthEndpoint",this.context).toString() + ",key1=value1,key2=value2")));
}
