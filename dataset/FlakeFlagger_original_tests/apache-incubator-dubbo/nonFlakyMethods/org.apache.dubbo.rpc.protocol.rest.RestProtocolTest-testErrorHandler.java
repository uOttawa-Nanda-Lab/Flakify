@Test(expected=RpcException.class) public void testErrorHandler(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  URL nettyUrl=exportUrl.addParameter(Constants.SERVER_KEY,"netty");
  Exporter<IDemoService> exporter=protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,nettyUrl));
  IDemoService demoService=this.proxy.getProxy(protocol.refer(IDemoService.class,nettyUrl));
  demoService.error();
}
