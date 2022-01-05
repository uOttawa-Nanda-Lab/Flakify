@Test public void testExport(){
  RegistryProtocol registryProtocol=new RegistryProtocol();
  registryProtocol.setCluster(new FailfastCluster());
  registryProtocol.setRegistryFactory(ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension());
  Protocol dubboProtocol=DubboProtocol.getDubboProtocol();
  registryProtocol.setProtocol(dubboProtocol);
  URL newRegistryUrl=registryUrl.addParameter(Constants.EXPORT_KEY,serviceUrl);
  DubboInvoker<DemoService> invoker=new DubboInvoker<DemoService>(DemoService.class,newRegistryUrl,new ExchangeClient[]{new MockedClient("10.20.20.20",2222,true)});
  Exporter<DemoService> exporter=registryProtocol.export(invoker);
  Exporter<DemoService> exporter2=registryProtocol.export(invoker);
  Assert.assertNotSame(exporter,exporter2);
  exporter.unexport();
  exporter2.unexport();
}
