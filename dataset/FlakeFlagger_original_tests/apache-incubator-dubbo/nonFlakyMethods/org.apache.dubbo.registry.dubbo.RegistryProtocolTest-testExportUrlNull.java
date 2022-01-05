@Test(expected=IllegalArgumentException.class) public void testExportUrlNull(){
  RegistryProtocol registryProtocol=new RegistryProtocol();
  registryProtocol.setCluster(new FailfastCluster());
  Protocol dubboProtocol=DubboProtocol.getDubboProtocol();
  registryProtocol.setProtocol(dubboProtocol);
  Invoker<DemoService> invoker=new DubboInvoker<DemoService>(DemoService.class,registryUrl,new ExchangeClient[]{new MockedClient("10.20.20.20",2222,true)});
  registryProtocol.export(invoker);
}
