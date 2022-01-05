@Test public void test_Normal_available(){
  URL url=URL.valueOf("dubbo://127.0.0.1:20883/org.apache.dubbo.rpc.protocol.dubbo.IDemoService");
  ProtocolUtils.export(new DemoServiceImpl(),IDemoService.class,url);
  DubboInvoker<?> invoker=(DubboInvoker<?>)protocol.refer(IDemoService.class,url);
  Assert.assertEquals(true,invoker.isAvailable());
  invoker.destroy();
  Assert.assertEquals(false,invoker.isAvailable());
}
