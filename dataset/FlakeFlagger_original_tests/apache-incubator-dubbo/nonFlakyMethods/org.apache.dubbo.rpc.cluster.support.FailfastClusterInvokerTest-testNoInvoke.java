@Test() public void testNoInvoke(){
  dic=mock(Directory.class);
  given(dic.getUrl()).willReturn(url);
  given(dic.list(invocation)).willReturn(null);
  given(dic.getInterface()).willReturn(FailfastClusterInvokerTest.class);
  invocation.setMethodName("method1");
  invokers.add(invoker1);
  resetInvoker1ToNoException();
  FailfastClusterInvoker<FailfastClusterInvokerTest> invoker=new FailfastClusterInvoker<FailfastClusterInvokerTest>(dic);
  try {
    invoker.invoke(invocation);
    fail();
  }
 catch (  RpcException expected) {
    assertFalse(expected.getCause() instanceof RpcException);
  }
}
