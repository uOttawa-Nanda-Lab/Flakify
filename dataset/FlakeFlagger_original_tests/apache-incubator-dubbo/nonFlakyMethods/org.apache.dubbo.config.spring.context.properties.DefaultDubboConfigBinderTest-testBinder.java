@Test public void testBinder(){
  ApplicationConfig applicationConfig=new ApplicationConfig();
  dubboConfigBinder.bind("dubbo.application",applicationConfig);
  Assert.assertEquals("hello",applicationConfig.getName());
  Assert.assertEquals("world",applicationConfig.getOwner());
  RegistryConfig registryConfig=new RegistryConfig();
  dubboConfigBinder.bind("dubbo.registry",registryConfig);
  Assert.assertEquals("10.20.153.17",registryConfig.getAddress());
  ProtocolConfig protocolConfig=new ProtocolConfig();
  dubboConfigBinder.bind("dubbo.protocol",protocolConfig);
  Assert.assertEquals(Integer.valueOf(20881),protocolConfig.getPort());
}
