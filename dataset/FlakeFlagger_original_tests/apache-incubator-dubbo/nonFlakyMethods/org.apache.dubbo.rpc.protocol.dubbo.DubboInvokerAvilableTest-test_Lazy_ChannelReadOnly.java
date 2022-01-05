@Test public void test_Lazy_ChannelReadOnly() throws Exception {
  URL url=URL.valueOf("dubbo://127.0.0.1:20883/org.apache.dubbo.rpc.protocol.dubbo.IDemoService?lazy=true&connections=1&timeout=10000");
  ProtocolUtils.export(new DemoServiceImpl(),IDemoService.class,url);
  DubboInvoker<?> invoker=(DubboInvoker<?>)protocol.refer(IDemoService.class,url);
  Assert.assertEquals(true,invoker.isAvailable());
  try {
    getClients(invoker)[0].setAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY,Boolean.TRUE);
    fail();
  }
 catch (  IllegalStateException e) {
  }
  IDemoService service=(IDemoService)proxy.getProxy(invoker);
  Assert.assertEquals("ok",service.get());
  Assert.assertEquals(true,invoker.isAvailable());
  getClients(invoker)[0].setAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY,Boolean.TRUE);
  Assert.assertEquals(false,invoker.isAvailable());
}
