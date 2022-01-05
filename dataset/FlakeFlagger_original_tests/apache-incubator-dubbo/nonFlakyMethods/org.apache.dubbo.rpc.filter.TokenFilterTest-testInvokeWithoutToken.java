@Test(expected=RpcException.class) public void testInvokeWithoutToken() throws Exception {
  String token="token";
  Invoker invoker=Mockito.mock(Invoker.class);
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&token=" + token);
  when(invoker.getUrl()).thenReturn(url);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult("result"));
  Invocation invocation=Mockito.mock(Invocation.class);
  tokenFilter.invoke(invoker,invocation);
}
