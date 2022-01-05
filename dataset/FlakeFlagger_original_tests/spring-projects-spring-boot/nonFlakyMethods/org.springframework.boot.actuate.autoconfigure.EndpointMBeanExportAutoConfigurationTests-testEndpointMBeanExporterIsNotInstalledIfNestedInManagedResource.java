@Test public void testEndpointMBeanExporterIsNotInstalledIfNestedInManagedResource() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,JmxAutoConfiguration.class,NestedInManagedEndpoint.class,EndpointMBeanExportAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(EndpointMBeanExporter.class));
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertTrue(mbeanExporter.getServer().queryNames(getObjectName("*","*,*",this.context),null).isEmpty());
}
