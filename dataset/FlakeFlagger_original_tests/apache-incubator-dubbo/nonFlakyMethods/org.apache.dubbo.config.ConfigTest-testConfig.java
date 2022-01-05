@Test public void testConfig(){
  com.alibaba.dubbo.config.ServiceConfig<DemoService> service=new ServiceConfig<>();
  service.setApplication(new com.alibaba.dubbo.config.ApplicationConfig("first-dubbo-provider"));
  service.setRegistry(new com.alibaba.dubbo.config.RegistryConfig("multicast://224.5.6.7:1234"));
  service.setInterface(DemoService.class);
  service.setRef(new DemoServiceImpl());
  service.export();
  com.alibaba.dubbo.config.ReferenceConfig<DemoService> reference=new ReferenceConfig<>();
  reference.setApplication(new ApplicationConfig("first-dubbo-client"));
  reference.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
  reference.setInterface(DemoService.class);
  DemoService demoService=reference.get();
  String message=demoService.sayHello("dubbo");
  Assert.assertEquals("hello dubbo",message);
}
