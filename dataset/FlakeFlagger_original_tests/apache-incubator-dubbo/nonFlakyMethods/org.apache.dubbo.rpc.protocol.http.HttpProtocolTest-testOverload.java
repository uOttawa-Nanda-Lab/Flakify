@Test public void testOverload(){
  HttpServiceImpl server=new HttpServiceImpl();
  Assert.assertFalse(server.isCalled());
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("http://127.0.0.1:5342/" + HttpService.class.getName() + "?version=1.0.0&hessian.overload.method=true&hessian2.request=false");
  Exporter<HttpService> exporter=protocol.export(proxyFactory.getInvoker(server,HttpService.class,url));
  Invoker<HttpService> invoker=protocol.refer(HttpService.class,url);
  HttpService client=proxyFactory.getProxy(invoker);
  String result=client.sayHello("haha");
  Assert.assertEquals("Hello, haha",result);
  result=client.sayHello("haha",1);
  Assert.assertEquals("Hello, haha. ",result);
  invoker.destroy();
  exporter.unexport();
}
