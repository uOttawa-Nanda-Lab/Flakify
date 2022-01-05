@Test public void testInvokerNonJsonEnumSerialization(){
  invocation=mock(Invocation.class);
  given(invocation.getMethodName()).willReturn("enumlength");
  given(invocation.getParameterTypes()).willReturn(new Class<?>[]{Type[].class});
  given(invocation.getArguments()).willReturn(new Object[]{"hello"});
  invoker=mock(Invoker.class);
  given(invoker.isAvailable()).willReturn(true);
  given(invoker.getInterface()).willReturn(DemoService.class);
  RpcResult result=new RpcResult();
  result.setValue("High");
  given(invoker.invoke(invocation)).willReturn(result);
  URL url=URL.valueOf("test://test:11/test?group=dubbo&version=1.1");
  given(invoker.getUrl()).willReturn(url);
  Result filterResult=compatibleFilter.invoke(invoker,invocation);
  assertEquals(Type.High,filterResult.getValue());
}
