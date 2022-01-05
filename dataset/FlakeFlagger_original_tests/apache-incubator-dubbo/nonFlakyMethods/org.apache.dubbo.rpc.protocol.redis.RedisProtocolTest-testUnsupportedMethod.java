@Test(expected=RpcException.class) public void testUnsupportedMethod(){
  Invoker<IDemoService> refer=protocol.refer(IDemoService.class,registryUrl);
  IDemoService demoService=this.proxy.getProxy(refer);
  demoService.unsupported(null);
}
