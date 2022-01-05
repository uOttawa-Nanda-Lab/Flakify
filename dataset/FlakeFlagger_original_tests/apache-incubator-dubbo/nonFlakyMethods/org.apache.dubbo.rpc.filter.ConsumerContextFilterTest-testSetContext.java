@Test public void testSetContext(){
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  Invoker<DemoService> invoker=new MyInvoker<DemoService>(url);
  Invocation invocation=new MockInvocation();
  consumerContextFilter.invoke(invoker,invocation);
  assertEquals(invoker,RpcContext.getContext().getInvoker());
  assertEquals(invocation,RpcContext.getContext().getInvocation());
  assertEquals(NetUtils.getLocalHost() + ":0",RpcContext.getContext().getLocalAddressString());
  assertEquals("test:11",RpcContext.getContext().getRemoteAddressString());
}
