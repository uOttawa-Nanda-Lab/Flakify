@Test public void testSyncCallback(){
  @SuppressWarnings("unchecked") Invoker<DemoService> invoker=mock(Invoker.class);
  given(invoker.isAvailable()).willReturn(true);
  given(invoker.getInterface()).willReturn(DemoService.class);
  RpcResult result=new RpcResult();
  result.setValue("High");
  given(invoker.invoke(invocation)).willReturn(result);
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  given(invoker.getUrl()).willReturn(url);
  Result filterResult=eventFilter.invoke(invoker,invocation);
  assertEquals("High",filterResult.getValue());
}
