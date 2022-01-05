@Test public void testInvokeWithTimeout() throws Exception {
  int timeout=100;
  URL url=URL.valueOf("test://test:11/test?accesslog=true&group=dubbo&version=1.1&timeout=" + timeout);
  Invoker invoker=new BlockMyInvoker(url,(timeout + 100));
  Invocation invocation=Mockito.mock(Invocation.class);
  when(invocation.getMethodName()).thenReturn("testInvokeWithTimeout");
  Result result=timeoutFilter.invoke(invoker,invocation);
  Assert.assertEquals("alibaba",result.getValue());
}
