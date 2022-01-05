@Test public void testInvokeWithRuntimeException(){
  given(invoker1.invoke(invocation)).willThrow(new RuntimeException());
  given(invoker1.isAvailable()).willReturn(true);
  given(invoker1.getUrl()).willReturn(url);
  given(invoker1.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  given(invoker2.invoke(invocation)).willThrow(new RuntimeException());
  given(invoker2.isAvailable()).willReturn(true);
  given(invoker2.getUrl()).willReturn(url);
  given(invoker2.getInterface()).willReturn(FailoverClusterInvokerTest.class);
  FailoverClusterInvoker<FailoverClusterInvokerTest> invoker=new FailoverClusterInvoker<FailoverClusterInvokerTest>(dic);
  try {
    invoker.invoke(invocation);
    fail();
  }
 catch (  RpcException expected) {
    assertEquals(0,expected.getCode());
    assertFalse(expected.getCause() instanceof RpcException);
  }
}
