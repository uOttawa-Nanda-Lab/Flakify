@Test(expected=RpcException.class) public void testInvokeExceptoin(){
  resetInvoker1ToException();
  FailfastClusterInvoker<FailfastClusterInvokerTest> invoker=new FailfastClusterInvoker<FailfastClusterInvokerTest>(dic);
  invoker.invoke(invocation);
  Assert.assertSame(invoker1,RpcContext.getContext().getInvoker());
}
