@Test() public void testRetryFailed(){
  resetInvokerToException();
  FailbackClusterInvoker<FailbackClusterInvokerTest> invoker=new FailbackClusterInvoker<FailbackClusterInvokerTest>(dic);
  invoker.invoke(invocation);
  Assert.assertNull(RpcContext.getContext().getInvoker());
  invoker.retryFailed();
}
