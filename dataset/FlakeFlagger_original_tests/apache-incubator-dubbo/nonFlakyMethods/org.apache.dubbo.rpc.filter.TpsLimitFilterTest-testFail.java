@Test(expected=RpcException.class) public void testFail() throws Exception {
  URL url=URL.valueOf("test://test");
  url=url.addParameter(Constants.INTERFACE_KEY,"org.apache.dubbo.rpc.file.TpsService");
  url=url.addParameter(Constants.TPS_LIMIT_RATE_KEY,5);
  Invoker<TpsLimitFilterTest> invoker=new MyInvoker<TpsLimitFilterTest>(url);
  Invocation invocation=new MockInvocation();
  for (int i=0; i < 10; i++) {
    try {
      filter.invoke(invoker,invocation);
    }
 catch (    Exception e) {
      assertTrue(i >= 5);
      throw e;
    }
  }
}
