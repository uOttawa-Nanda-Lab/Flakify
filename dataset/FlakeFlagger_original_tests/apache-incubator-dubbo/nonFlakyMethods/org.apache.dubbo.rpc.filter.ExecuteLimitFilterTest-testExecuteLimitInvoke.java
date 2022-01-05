@Test public void testExecuteLimitInvoke() throws Exception {
  Invoker invoker=Mockito.mock(Invoker.class);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult("result"));
  when(invoker.getUrl()).thenReturn(URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&executes=10"));
  Invocation invocation=Mockito.mock(Invocation.class);
  when(invocation.getMethodName()).thenReturn("testExecuteLimitInvoke");
  Result result=executeLimitFilter.invoke(invoker,invocation);
  Assert.assertEquals("result",result.getValue());
}
