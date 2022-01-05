@Test public void testGenericInvokeWithRpcContext(){
  RpcContext.getContext().setAttachment("myContext","123");
  HessianServiceImpl server=new HessianServiceImpl();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("hessian://127.0.0.1:5342/" + HessianService.class.getName() + "?version=1.0.0");
  Exporter<HessianService> exporter=protocol.export(proxyFactory.getInvoker(server,HessianService.class,url));
  Invoker<GenericService> invoker=protocol.refer(GenericService.class,url);
  GenericService client=proxyFactory.getProxy(invoker,true);
  String result=(String)client.$invoke("context",new String[]{"java.lang.String"},new Object[]{"haha"});
  Assert.assertEquals("Hello, haha context, 123",result);
  invoker.destroy();
  exporter.unexport();
}
