/** 
 * unit test for dubbo-1765
 */
@Test public void testReferenceRetry(){
  ApplicationConfig application=new ApplicationConfig();
  application.setName("test-reference-retry");
  RegistryConfig registry=new RegistryConfig();
  registry.setAddress("multicast://224.5.6.7:1234");
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setName("dubbo");
  ReferenceConfig<DemoService> rc=new ReferenceConfig<DemoService>();
  rc.setApplication(application);
  rc.setRegistry(registry);
  rc.setInterface(DemoService.class.getName());
  boolean success=false;
  DemoService demoService=null;
  try {
    demoService=rc.get();
    success=true;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  Assert.assertFalse(success);
  Assert.assertNull(demoService);
  ServiceConfig<DemoService> sc=new ServiceConfig<DemoService>();
  sc.setInterface(DemoService.class);
  sc.setRef(new DemoServiceImpl());
  sc.setApplication(application);
  sc.setRegistry(registry);
  sc.setProtocol(protocol);
  try {
    sc.export();
    demoService=rc.get();
    success=true;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  Assert.assertTrue(success);
  Assert.assertNotNull(demoService);
}
