@Test public void testInvokeException(){
  Invoker<AccessLogFilterTest> invoker=new MyInvoker<AccessLogFilterTest>(null);
  Invocation invocation=new MockInvocation();
  LogUtil.start();
  accessLogFilter.invoke(invoker,invocation);
  assertEquals(1,LogUtil.findMessage("Exception in AcessLogFilter of service"));
  LogUtil.stop();
}
