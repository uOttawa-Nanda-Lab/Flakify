@Test public void testGenericInvoke(){
  HttpServiceImpl server=new HttpServiceImpl();
  Assert.assertFalse(server.isCalled());
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("http://127.0.0.1:5342/" + HttpService.class.getName() + "?version=1.0.0");
  Exporter<HttpService> exporter=protocol.export(proxyFactory.getInvoker(server,HttpService.class,url));
  Invoker<GenericService> invoker=protocol.refer(GenericService.class,url);
  GenericService client=proxyFactory.getProxy(invoker,true);
  String result=(String)client.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"haha"});
  Assert.assertTrue(server.isCalled());
  Assert.assertEquals("Hello, haha",result);
  invoker.destroy();
  exporter.unexport();
}
