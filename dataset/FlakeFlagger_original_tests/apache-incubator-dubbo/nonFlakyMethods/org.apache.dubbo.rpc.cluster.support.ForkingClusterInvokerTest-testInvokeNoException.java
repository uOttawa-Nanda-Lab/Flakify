@Test() public void testInvokeNoException(){
  resetInvokerToNoException();
  ForkingClusterInvoker<ForkingClusterInvokerTest> invoker=new ForkingClusterInvoker<ForkingClusterInvokerTest>(dic);
  Result ret=invoker.invoke(invocation);
  Assert.assertSame(result,ret);
}
