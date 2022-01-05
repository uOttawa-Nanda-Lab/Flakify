@Test public void testFilter(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  URL nettyUrl=exportUrl.addParameter(Constants.SERVER_KEY,"netty").addParameter(Constants.EXTENSION_KEY,"org.apache.dubbo.rpc.protocol.rest.support.LoggingFilter");
  Exporter<IDemoService> exporter=protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,nettyUrl));
  IDemoService demoService=this.proxy.getProxy(protocol.refer(IDemoService.class,nettyUrl));
  Integer result=demoService.hello(1,2);
  assertThat(result,is(3));
  exporter.unexport();
}
