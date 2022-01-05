@Test public void testSticky2(){
  URL url=URL.valueOf("dubbo://127.0.0.1:9090/org.apache.dubbo.rpc.protocol.dubbo.IDemoService?" + Constants.LAZY_CONNECT_KEY + "=true");
  ProtocolUtils.refer(IDemoService.class,url);
}
