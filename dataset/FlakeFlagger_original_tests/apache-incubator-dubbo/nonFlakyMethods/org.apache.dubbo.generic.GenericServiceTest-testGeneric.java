@Test public void testGeneric(){
  DemoService server=new DemoServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("dubbo://127.0.0.1:5342/" + DemoService.class.getName() + "?version=1.0.0");
  Exporter<DemoService> exporter=protocol.export(proxyFactory.getInvoker(server,DemoService.class,url));
  Invoker<DemoService> invoker=protocol.refer(DemoService.class,url);
  GenericService client=(GenericService)proxyFactory.getProxy(invoker,true);
  Object result=client.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"haha"});
  Assert.assertEquals("hello haha",result);
  org.apache.dubbo.rpc.service.GenericService newClient=(org.apache.dubbo.rpc.service.GenericService)proxyFactory.getProxy(invoker,true);
  Object res=newClient.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"hehe"});
  Assert.assertEquals("hello hehe",res);
  invoker.destroy();
  exporter.unexport();
}
