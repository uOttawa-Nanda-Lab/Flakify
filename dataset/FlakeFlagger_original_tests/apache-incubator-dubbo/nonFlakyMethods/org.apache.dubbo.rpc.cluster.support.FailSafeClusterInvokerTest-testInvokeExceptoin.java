@Test public void testInvokeExceptoin(){
  resetInvokerToException();
  FailsafeClusterInvoker<DemoService> invoker=new FailsafeClusterInvoker<DemoService>(dic);
  invoker.invoke(invocation);
  Assert.assertNull(RpcContext.getContext().getInvoker());
}
