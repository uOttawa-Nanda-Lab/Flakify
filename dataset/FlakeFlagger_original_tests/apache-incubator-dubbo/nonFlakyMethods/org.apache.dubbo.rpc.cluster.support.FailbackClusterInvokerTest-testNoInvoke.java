@Test() public void testNoInvoke(){
  dic=mock(Directory.class);
  given(dic.getUrl()).willReturn(url);
  given(dic.list(invocation)).willReturn(null);
  given(dic.getInterface()).willReturn(FailbackClusterInvokerTest.class);
  invocation.setMethodName("method1");
  invokers.add(invoker);
  resetInvokerToNoException();
  FailbackClusterInvoker<FailbackClusterInvokerTest> invoker=new FailbackClusterInvoker<FailbackClusterInvokerTest>(dic);
  LogUtil.start();
  invoker.invoke(invocation);
  assertEquals(1,LogUtil.findMessage("Failback to invoke"));
  LogUtil.stop();
}
