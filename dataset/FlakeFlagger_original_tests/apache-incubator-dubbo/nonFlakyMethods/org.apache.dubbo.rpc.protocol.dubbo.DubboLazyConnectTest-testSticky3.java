@Test(expected=RpcException.class) public void testSticky3(){
  URL url=URL.valueOf("dubbo://127.0.0.1:9090/org.apache.dubbo.rpc.protocol.dubbo.IDemoService?" + Constants.LAZY_CONNECT_KEY + "=true");
  IDemoService service=(IDemoService)ProtocolUtils.refer(IDemoService.class,url);
  service.get();
}
