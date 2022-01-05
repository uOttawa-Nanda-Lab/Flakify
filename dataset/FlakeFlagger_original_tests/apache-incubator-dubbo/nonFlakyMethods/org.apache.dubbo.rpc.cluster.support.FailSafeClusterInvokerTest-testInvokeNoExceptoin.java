@Test() public void testInvokeNoExceptoin(){
  resetInvokerToNoException();
  FailsafeClusterInvoker<DemoService> invoker=new FailsafeClusterInvoker<DemoService>(dic);
  Result ret=invoker.invoke(invocation);
  Assert.assertSame(result,ret);
}
