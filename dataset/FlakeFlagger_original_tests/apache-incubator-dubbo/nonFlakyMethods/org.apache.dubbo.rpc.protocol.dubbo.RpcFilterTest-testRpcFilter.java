@Test public void testRpcFilter() throws Exception {
  DemoService service=new DemoServiceImpl();
  URL url=URL.valueOf("dubbo://127.0.0.1:9010/org.apache.dubbo.rpc.DemoService?service.filter=echo");
  protocol.export(proxy.getInvoker(service,DemoService.class,url));
  service=proxy.getProxy(protocol.refer(DemoService.class,url));
  Assert.assertEquals("123",service.echo("123"));
  EchoService echo=proxy.getProxy(protocol.refer(EchoService.class,url));
  Assert.assertEquals(echo.$echo("test"),"test");
  Assert.assertEquals(echo.$echo("abcdefg"),"abcdefg");
  Assert.assertEquals(echo.$echo(1234),1234);
}
