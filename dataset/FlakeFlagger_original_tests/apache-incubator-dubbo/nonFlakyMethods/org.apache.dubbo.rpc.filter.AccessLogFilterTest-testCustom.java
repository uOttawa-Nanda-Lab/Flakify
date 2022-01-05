@Test public void testCustom(){
  URL url=URL.valueOf("test://test:11/test?accesslog=custom-access.log");
  Invoker<AccessLogFilterTest> invoker=new MyInvoker<AccessLogFilterTest>(url);
  Invocation invocation=new MockInvocation();
  accessLogFilter.invoke(invoker,invocation);
}
