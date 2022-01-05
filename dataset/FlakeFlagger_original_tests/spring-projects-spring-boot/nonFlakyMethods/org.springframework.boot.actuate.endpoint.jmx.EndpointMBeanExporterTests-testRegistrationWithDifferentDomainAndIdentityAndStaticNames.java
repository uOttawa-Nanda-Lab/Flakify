@Test public void testRegistrationWithDifferentDomainAndIdentityAndStaticNames() throws Exception {
  Map<String,Object> properties=new HashMap<String,Object>();
  properties.put("domain","test-domain");
  properties.put("ensureUniqueRuntimeObjectNames",true);
  Properties staticNames=new Properties();
  staticNames.put("key1","value1");
  staticNames.put("key2","value2");
  properties.put("objectNameStaticProperties",staticNames);
  this.context=new GenericApplicationContext();
  this.context.registerBeanDefinition("endpointMbeanExporter",new RootBeanDefinition(EndpointMBeanExporter.class,null,new MutablePropertyValues(properties)));
  this.context.registerBeanDefinition("endpoint1",new RootBeanDefinition(TestEndpoint.class));
  this.context.refresh();
  MBeanExporter mbeanExporter=this.context.getBean(EndpointMBeanExporter.class);
  assertNotNull(mbeanExporter.getServer().getMBeanInfo(ObjectNameManager.getInstance(getObjectName("test-domain","endpoint1",true,this.context).toString() + ",key1=value1,key2=value2")));
}
