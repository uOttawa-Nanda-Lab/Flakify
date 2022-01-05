@Test public void testCustomException(){
  HessianServiceImpl server=new HessianServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("hessian://127.0.0.1:5342/" + HessianService.class.getName() + "?version=1.0.0");
  Exporter<HessianService> exporter=protocol.export(proxyFactory.getInvoker(server,HessianService.class,url));
  Invoker<HessianService> invoker=protocol.refer(HessianService.class,url);
  HessianService client=proxyFactory.getProxy(invoker);
  try {
    client.customException();
    fail();
  }
 catch (  MyException expected) {
  }
  invoker.destroy();
  exporter.unexport();
}
