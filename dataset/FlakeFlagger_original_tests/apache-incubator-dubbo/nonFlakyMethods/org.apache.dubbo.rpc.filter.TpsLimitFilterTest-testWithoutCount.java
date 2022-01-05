@Test public void testWithoutCount() throws Exception {
  URL url=URL.valueOf("test://test");
  url=url.addParameter(Constants.INTERFACE_KEY,"org.apache.dubbo.rpc.file.TpsService");
  url=url.addParameter(Constants.TPS_LIMIT_RATE_KEY,5);
  Invoker<TpsLimitFilterTest> invoker=new MyInvoker<TpsLimitFilterTest>(url);
  Invocation invocation=new MockInvocation();
  filter.invoke(invoker,invocation);
}
