@Test public void testGenericInvokeWithBean(){
  HessianServiceImpl server=new HessianServiceImpl();
  Assert.assertFalse(server.isCalled());
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  URL url=URL.valueOf("hessian://127.0.0.1:5342/" + HessianService.class.getName() + "?version=1.0.0&generic=bean");
  Exporter<HessianService> exporter=protocol.export(proxyFactory.getInvoker(server,HessianService.class,url));
  Invoker<GenericService> invoker=protocol.refer(GenericService.class,url);
  GenericService client=proxyFactory.getProxy(invoker);
  JavaBeanDescriptor javaBeanDescriptor=JavaBeanSerializeUtil.serialize("haha");
  Object result=client.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{javaBeanDescriptor});
  Assert.assertTrue(server.isCalled());
  Assert.assertEquals("Hello, haha",JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor)result));
  invoker.destroy();
  exporter.unexport();
}
