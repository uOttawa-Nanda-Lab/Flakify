@Test public void testRegistrationWithDifferentDomainAndIdentity() throws Exception {
  Map<String,Object> properties=new HashMap<String,Object>();
  properties.put("domain","test-domain");
  properties.put("ensureUniqueRuntimeObjectNames",true);
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class,null,new MutablePropertyValues(properties)));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(getObjectName("test-domain","endpoint1",true,this.context)));
}
