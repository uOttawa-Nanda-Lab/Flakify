@Test public void testInvokeWithException() throws Exception {
  RpcInvocation invocation=new RpcInvocation("getPerson",new Class[]{Person.class},new Object[]{new Person("dubbo",10)});
  URL url=URL.valueOf("test://test:11/com.alibaba.dubbo.rpc.support.DemoService?" + "accesslog=true&group=dubbo&version=1.1&generic=true");
  Invoker invoker=Mockito.mock(Invoker.class);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult(new GenericException(new RuntimeException("failed"))));
  when(invoker.getUrl()).thenReturn(url);
  when(invoker.getInterface()).thenReturn(DemoService.class);
  Result result=genericImplFilter.invoke(invoker,invocation);
  Assert.assertEquals(RuntimeException.class,result.getException().getClass());
}
