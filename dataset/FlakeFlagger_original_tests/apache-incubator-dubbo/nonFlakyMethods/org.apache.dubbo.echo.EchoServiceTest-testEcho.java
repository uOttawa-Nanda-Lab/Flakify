@Test public void testEcho(){
  DemoService server=new DemoServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("dubbo://127.0.0.1:5342/" + DemoService.class.getName() + "?version=1.0.0");
  Exporter<DemoService> exporter=protocol.export(proxyFactory.getInvoker(server,DemoService.class,url));
  Invoker<DemoService> invoker=protocol.refer(DemoService.class,url);
  EchoService client=(EchoService)proxyFactory.getProxy(invoker);
  Object result=client.$echo("haha");
  Assert.assertEquals("haha",result);
  org.apache.dubbo.rpc.service.EchoService newClient=(org.apache.dubbo.rpc.service.EchoService)proxyFactory.getProxy(invoker);
  Object res=newClient.$echo("hehe");
  Assert.assertEquals("hehe",res);
  invoker.destroy();
  exporter.unexport();
}
