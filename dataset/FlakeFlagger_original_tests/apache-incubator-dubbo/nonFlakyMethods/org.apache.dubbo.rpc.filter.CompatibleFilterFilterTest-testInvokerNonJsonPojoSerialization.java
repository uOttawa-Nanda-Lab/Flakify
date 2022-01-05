@Test public void testInvokerNonJsonPojoSerialization(){
  invocation=mock(Invocation.class);
  given(invocation.getMethodName()).willReturn("echo");
  given(invocation.getParameterTypes()).willReturn(new Class<?>[]{String.class});
  given(invocation.getArguments()).willReturn(new Object[]{"hello"});
  invoker=mock(Invoker.class);
  given(invoker.isAvailable()).willReturn(true);
  given(invoker.getInterface()).willReturn(DemoService.class);
  RpcResult result=new RpcResult();
  result.setValue("hello");
  given(invoker.invoke(invocation)).willReturn(result);
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  given(invoker.getUrl()).willReturn(url);
  Result filterResult=compatibleFilter.invoke(invoker,invocation);
  assertEquals("hello",filterResult.getValue());
}
