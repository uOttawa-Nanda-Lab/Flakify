@Test() public void testInvoke_retryTimes(){
  given(invoker1.invoke(invocation)).willThrow(new RpcException(RpcException.TIMEOUT_EXCEPTION));
  given(invoker1.isAvailable()).willReturn(false);
  given(invoker1.getUrl()).willReturn(url);
  given(invoker1.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  given(invoker2.invoke(invocation)).willThrow(new RpcException());
  given(invoker2.isAvailable()).willReturn(false);
  given(invoker2.getUrl()).willReturn(url);
  given(invoker2.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  FailoverClusterInvoker<FailoverClusterInvokerTest> invoker=new FailoverClusterInvoker<FailoverClusterInvokerTest>(dic);
  try {
    Result ret=invoker.invoke(invocation);
    assertSame(result,ret);
    fail();
  }
 catch (  RpcException expected) {
    assertTrue((expected.isTimeout() || expected.getCode() == 0));
    assertTrue(expected.getMessage().indexOf((retries + 1) + " times") > 0);
  }
}
