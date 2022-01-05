@Test(expected=RuntimeException.class) public void testSyncCallbackHasException() throws RpcException, Throwable {
  @SuppressWarnings("unchecked") Invoker<DemoService> invoker=mock(Invoker.class);
  given(invoker.isAvailable()).willReturn(true);
  given(invoker.getInterface()).willReturn(DemoService.class);
  RpcResult result=new RpcResult();
  result.setException(new RuntimeException());
  given(invoker.invoke(invocation)).willReturn(result);
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1&" + Constants.ON_THROW_METHOD_KEY + "=echo");
  given(invoker.getUrl()).willReturn(url);
  eventFilter.invoke(invoker,invocation).recreate();
}
