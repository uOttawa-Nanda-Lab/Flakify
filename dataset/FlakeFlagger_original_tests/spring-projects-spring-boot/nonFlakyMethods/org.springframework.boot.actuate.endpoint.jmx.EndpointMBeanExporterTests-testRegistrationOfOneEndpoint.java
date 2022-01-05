@Test public void testRegistrationOfOneEndpoint() throws Exception {
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  MBeanInfo mbeanInfo=mbeanExporter.getServer().getMBeanInfo(getObjectName("endpoint1",this.context));
  assertNotNull(mbeanInfo);
  assertEquals(3,mbeanInfo.getOperations().length);
  assertEquals(3,mbeanInfo.getAttributes().length);
}
