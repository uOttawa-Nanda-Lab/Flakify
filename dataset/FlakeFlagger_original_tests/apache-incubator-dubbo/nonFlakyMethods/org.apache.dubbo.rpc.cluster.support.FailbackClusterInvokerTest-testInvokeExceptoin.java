@Test public void testInvokeExceptoin(){
  resetInvokerToException();
  FailbackClusterInvoker<FailbackClusterInvokerTest> invoker=new FailbackClusterInvoker<FailbackClusterInvokerTest>(dic);
  invoker.invoke(invocation);
  Assert.assertNull(RpcContext.getContext().getInvoker());
}
