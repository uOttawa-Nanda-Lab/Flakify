@Test public void testRegistrationWithDifferentDomain() throws Exception {
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class,null,new MutablePropertyValues(Collections.singletonMap("domain","test-domain"))));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("test-domain","endpoint1",false,this.context)));
}
