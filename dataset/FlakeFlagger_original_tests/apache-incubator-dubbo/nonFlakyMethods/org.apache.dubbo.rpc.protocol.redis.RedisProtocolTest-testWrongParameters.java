@Test(expected=RpcException.class) public void testWrongParameters(){
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,registryUrl);
  IDemoService demoService=this.proxy.getProxy(refer);
  demoService.set("key","value","wrongValue");
}
