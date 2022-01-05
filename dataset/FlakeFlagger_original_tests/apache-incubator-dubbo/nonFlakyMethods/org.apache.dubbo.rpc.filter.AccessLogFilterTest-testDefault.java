@Test public void testDefault(){
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1");
  Invoker<AccessLogFilterTest> invoker=new MyInvoker<AccessLogFilterTest>(url);
  Invocation invocation=new MockInvocation();
  accessLogFilter.invoke(invoker,invocation);
}
