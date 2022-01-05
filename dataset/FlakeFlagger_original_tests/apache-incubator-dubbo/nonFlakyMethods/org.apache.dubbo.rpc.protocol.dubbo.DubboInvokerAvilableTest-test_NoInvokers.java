@Test public void test_NoInvokers() throws Exception {
  URL url=URL.valueOf("dubbo://127.0.0.1:20883/org.apache.dubbo.rpc.protocol.dubbo.IDemoService?connections=1");
  ProtocolUtils.export(new DemoServiceImpl(),IDemoService.class,url);
  DubboInvoker<?> invoker=(DubboInvoker<?>)protocol.refer(IDemoService.class,url);
  ExchangeClient[] clients=getClients(invoker);
  clients[0].close();
  Assert.assertEquals(false,invoker.isAvailable());
}
