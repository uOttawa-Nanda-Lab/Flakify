@Test public void testExecuteLimitInvokeWitException() throws Exception {
  Invoker invoker=Mockito.mock(Invoker.class);
  doThrow(new RpcException()).when(invoker).invoke(any(Invocation.class));
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&executes=10");
  when(invoker.getUrl()).thenReturn(url);
  Invocation invocation=Mockito.mock(Invocation.class);
  when(invocation.getMethodName()).thenReturn("testExecuteLimitInvokeWitException");
  try {
    executeLimitFilter.invoke(invoker,invocation);
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof RpcException);
  }
  Assert.assertEquals(1,RpcStatus.getStatus(url,invocation.getMethodName()).getFailed());
}
