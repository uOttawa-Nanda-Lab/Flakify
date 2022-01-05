@Test public void testExport(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  RpcContext.getContext().setAttachment("timeout","200");
  Exporter<IDemoService> exporter=protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,exportUrl));
  IDemoService demoService=this.proxy.getProxy(protocol.refer(IDemoService.class,exportUrl));
  Integer echoString=demoService.hello(1,2);
  assertThat(echoString,is(3));
  exporter.unexport();
}
