@Test public void testHessianProtocol(){
  HessianServiceImpl server=new HessianServiceImpl();
  Assert.assertFalse(server.isCalled());
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("hessian://127.0.0.1:5342/" + HessianService.class.getName() + "?version=1.0.0&hessian.overload.method=true");
  Exporter<HessianService> exporter=protocol.export(proxyFactory.getInvoker(server,HessianService.class,url));
  Invoker<HessianService> invoker=protocol.refer(HessianService.class,url);
  HessianService client=proxyFactory.getProxy(invoker);
  String result=client.sayHello("haha");
  Assert.assertTrue(server.isCalled());
  Assert.assertEquals("Hello, haha",result);
  invoker.destroy();
  exporter.unexport();
}
