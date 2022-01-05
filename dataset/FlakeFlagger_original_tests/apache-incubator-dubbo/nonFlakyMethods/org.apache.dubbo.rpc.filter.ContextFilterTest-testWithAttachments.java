@Test public void testWithAttachments(){
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  Invoker<DemoService> invoker=new MyInvoker<DemoService>(url);
  Invocation invocation=new MockInvocation();
  Result result=contextFilter.invoke(invoker,invocation);
  assertNull(RpcContext.getContext().getInvoker());
}
