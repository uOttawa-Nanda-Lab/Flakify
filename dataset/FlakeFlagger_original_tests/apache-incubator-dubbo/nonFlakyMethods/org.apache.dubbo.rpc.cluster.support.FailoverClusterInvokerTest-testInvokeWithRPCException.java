@Test() public void testInvokeWithRPCException(){
  given(invoker1.invoke(invocation)).willThrow(new RpcException());
  given(invoker1.isAvailable()).willReturn(true);
  given(invoker1.getUrl()).willReturn(url);
  given(invoker1.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  given(invoker2.invoke(invocation)).willReturn(result);
  given(invoker2.isAvailable()).willReturn(true);
  given(invoker2.getUrl()).willReturn(url);
  given(invoker2.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  FailoverClusterInvoker<FailoverClusterInvokerTest> invoker=new FailoverClusterInvoker<FailoverClusterInvokerTest>(dic);
  for (int i=0; i < 100; i++) {
    Result ret=invoker.invoke(invocation);
    assertSame(result,ret);
  }
}
