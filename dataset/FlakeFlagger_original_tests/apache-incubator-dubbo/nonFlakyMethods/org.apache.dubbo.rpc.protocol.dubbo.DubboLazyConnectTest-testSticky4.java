@Test public void testSticky4(){
  int port=NetUtils.getAvailablePort();
  URL url=URL.valueOf("dubbo://127.0.0.1:" + port + "/org.apache.dubbo.rpc.protocol.dubbo.IDemoService?"+ Constants.LAZY_CONNECT_KEY+ "=true&timeout=20000");
  ProtocolUtils.export(new DemoServiceImpl(),IDemoService.class,url);
  IDemoService service=(IDemoService)ProtocolUtils.refer(IDemoService.class,url);
  Assert.assertEquals("ok",service.get());
}
