@Test public void testDeprecatedFilter(){
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1&echo." + Constants.DEPRECATED_KEY + "=true");
  LogUtil.start();
  deprecatedFilter.invoke(new MyInvoker<DemoService>(url),new MockInvocation());
  assertEquals(1,LogUtil.findMessage("The service method org.apache.dubbo.rpc.support.DemoService.echo(String) is DEPRECATED"));
  LogUtil.stop();
}
