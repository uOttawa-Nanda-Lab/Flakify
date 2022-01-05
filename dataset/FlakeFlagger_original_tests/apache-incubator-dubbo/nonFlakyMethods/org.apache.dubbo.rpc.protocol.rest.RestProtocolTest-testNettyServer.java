@Test public void testNettyServer(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  URL nettyUrl=exportUrl.addParameter(Constants.SERVER_KEY,"netty");
  Exporter<IDemoService> exporter=protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,nettyUrl));
  IDemoService demoService=this.proxy.getProxy(protocol.refer(IDemoService.class,nettyUrl));
  Integer echoString=demoService.hello(10,10);
  assertThat(echoString,is(20));
  exporter.unexport();
}
