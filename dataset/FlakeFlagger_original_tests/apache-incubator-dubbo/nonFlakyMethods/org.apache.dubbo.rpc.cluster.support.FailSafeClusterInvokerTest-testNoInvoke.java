@Test() public void testNoInvoke(){
  dic=mock(Directory.class);
  given(dic.getUrl()).willReturn(url);
  given(dic.list(invocation)).willReturn(null);
  given(dic.getInterface()).willReturn(DemoService.class);
  invocation.setMethodName("method1");
  resetInvokerToNoException();
  FailsafeClusterInvoker<DemoService> invoker=new FailsafeClusterInvoker<DemoService>(dic);
  LogUtil.start();
  invoker.invoke(invocation);
  assertTrue(LogUtil.findMessage("No provider") > 0);
  LogUtil.stop();
}
