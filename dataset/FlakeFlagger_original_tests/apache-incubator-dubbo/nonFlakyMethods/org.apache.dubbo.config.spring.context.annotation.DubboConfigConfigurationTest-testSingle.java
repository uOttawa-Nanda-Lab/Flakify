@Test public void testSingle() throws IOException {
  context.register(DubboConfigConfiguration.Single.class);
  context.refresh();
  ApplicationConfig applicationConfig=context.getBean("applicationBean",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
  ModuleConfig moduleConfig=context.getBean("moduleBean",ModuleConfig.class);
  Assert.assertEquals("dubbo-demo-module",moduleConfig.getName());
  RegistryConfig registryConfig=context.getBean(RegistryConfig.class);
  Assert.assertEquals("zookeeper://192.168.99.100:32770",registryConfig.getAddress());
  ProtocolConfig protocolConfig=context.getBean(ProtocolConfig.class);
  Assert.assertEquals("dubbo",protocolConfig.getName());
  Assert.assertEquals(Integer.valueOf(20880),protocolConfig.getPort());
}
