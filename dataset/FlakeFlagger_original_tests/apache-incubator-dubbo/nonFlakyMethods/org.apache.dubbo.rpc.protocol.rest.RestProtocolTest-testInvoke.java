@Test public void testInvoke(){
  ServiceClassHolder.getInstance().pushServiceClass(DemoService.class);
  Exporter<IDemoService> exporter=protocol.export(proxy.getInvoker(new DemoService(),IDemoService.class,exportUrl));
  RpcInvocation rpcInvocation=new RpcInvocation("hello",new Class[]{Integer.class,Integer.class},new Integer[]{2,3});
  Result result=exporter.getInvoker().invoke(rpcInvocation);
  assertThat(result.getValue(),CoreMatchers.<Object>is(5));
}
