@Test public void testRestProtocolWithContextPath(){
  ServiceClassHolder.getInstance().pushServiceClass(RestServiceImpl.class);
  RestServiceImpl server=new RestServiceImpl();
  Assert.assertFalse(server.isCalled());
  URL url=URL.valueOf("rest://127.0.0.1:5341/a/b/c?version=1.0.0");
  Exporter<RestService> exporter=protocol.export(proxyFactory.getInvoker(server,RestService.class,url));
  url=URL.valueOf("rest://127.0.0.1:5341/a/b/c/?version=1.0.0");
  Invoker<RestService> invoker=protocol.refer(RestService.class,url);
  RestService client=proxyFactory.getProxy(invoker);
  String result=client.sayHello("haha");
  Assert.assertTrue(server.isCalled());
  Assert.assertEquals("Hello, haha",result);
  invoker.destroy();
  exporter.unexport();
}
