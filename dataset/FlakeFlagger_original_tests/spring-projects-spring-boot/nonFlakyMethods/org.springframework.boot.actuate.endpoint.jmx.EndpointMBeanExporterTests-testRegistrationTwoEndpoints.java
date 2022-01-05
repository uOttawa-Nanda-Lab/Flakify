@Test public void testRegistrationTwoEndpoints() throws Exception {
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  this.context.registerBeanDefinition("endpoint2",new RootBeanDefinition(TestEndpoint.class));
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("endpoint1",this.context)));
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("endpoint2",this.context)));
}
