@Test public void test_Normal_ChannelReadOnly() throws Exception {
  URL url=URL.valueOf("dubbo://127.0.0.1:20883/org.apache.dubbo.rpc.protocol.dubbo.IDemoService");
  ProtocolUtils.export(new DemoServiceImpl(),IDemoService.class,url);
  DubboInvoker<?> invoker=(DubboInvoker<?>)protocol.refer(IDemoService.class,url);
  Assert.assertEquals(true,invoker.isAvailable());
  getClients(invoker)[0].setAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY,Boolean.TRUE);
  Assert.assertEquals(false,invoker.isAvailable());
  getClients(invoker)[0].removeAttribute(Constants.CHANNEL_ATTRIBUTE_READONLY_KEY);
}
