@Test public void testGeneric2(){
  DemoService server=new DemoServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("dubbo://127.0.0.1:5342/" + DemoService.class.getName() + "?version=1.0.0&generic=true");
  Exporter<DemoService> exporter=protocol.export(proxyFactory.getInvoker(server,DemoService.class,url));
  Invoker<GenericService> invoker=protocol.refer(GenericService.class,url);
  GenericService client=proxyFactory.getProxy(invoker,true);
  Object result=client.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"haha"});
  Assert.assertEquals("hello haha",result);
  Invoker<DemoService> invoker2=protocol.refer(DemoService.class,url);
  GenericService client2=(GenericService)proxyFactory.getProxy(invoker2,true);
  Object result2=client2.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"haha"});
  Assert.assertEquals("hello haha",result2);
  invoker.destroy();
  exporter.unexport();
}
