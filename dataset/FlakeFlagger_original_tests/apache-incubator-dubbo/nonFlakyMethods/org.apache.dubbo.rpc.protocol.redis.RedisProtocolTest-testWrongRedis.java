@Test(expected=RpcException.class) public void testWrongRedis(){
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,URL.valueOf("redis://localhost:1"));
  IDemoService demoService=this.proxy.getProxy(refer);
  demoService.get("key");
}
