@Test public void testInvokeWithoutTimeout() throws Exception {
  int timeout=3000;
  Invoker invoker=Mockito.mock(Invoker.class);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult("result"));
  when(invoker.getUrl()).thenReturn(URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&timeout=" + timeout));
  Invocation invocation=Mockito.mock(Invocation.class);
  when(invocation.getMethodName()).thenReturn("testInvokeWithoutTimeout");
  Result result=timeoutFilter.invoke(invoker,invocation);
  Assert.assertEquals("result",result.getValue());
}
