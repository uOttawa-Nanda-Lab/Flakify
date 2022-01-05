@Test() public void testInvokeNoExceptoin(){
  resetInvokerToNoException();
  FailbackClusterInvoker<FailbackClusterInvokerTest> invoker=new FailbackClusterInvoker<FailbackClusterInvokerTest>(dic);
  Result ret=invoker.invoke(invocation);
  Assert.assertSame(result,ret);
}
