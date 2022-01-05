@Test public void testCustomException(){
  HttpServiceImpl server=new HttpServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("http://127.0.0.1:5342/" + HttpService.class.getName() + "?version=1.0.0");
  Exporter<HttpService> exporter=protocol.export(proxyFactory.getInvoker(server,HttpService.class,url));
  Invoker<HttpService> invoker=protocol.refer(HttpService.class,url);
  HttpService client=proxyFactory.getProxy(invoker);
  try {
    client.customException();
    fail();
  }
 catch (  HttpServiceImpl.MyException expected) {
  }
  invoker.destroy();
  exporter.unexport();
}
