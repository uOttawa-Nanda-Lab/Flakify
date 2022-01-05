@Test public void testRegistrationWithParentContext() throws Exception {
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  GenericApplicationContext parent=new GenericApplicationContext();
  this.context.setParent(parent);
  parent.refresh();
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("endpoint1",this.context)));
  parent.close();
}
