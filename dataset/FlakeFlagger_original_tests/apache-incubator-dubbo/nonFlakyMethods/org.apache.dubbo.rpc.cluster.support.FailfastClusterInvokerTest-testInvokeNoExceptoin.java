@Test() public void testInvokeNoExceptoin(){
  resetInvoker1ToNoException();
  FailfastClusterInvoker<FailfastClusterInvokerTest> invoker=new FailfastClusterInvoker<FailfastClusterInvokerTest>(dic);
  Result ret=invoker.invoke(invocation);
  Assert.assertSame(result,ret);
}
