@Test(expected=RpcException.class) public void testInvokeWithJavaException() throws Exception {
  Method genericInvoke=GenericService.class.getMethods()[0];
  Map<String,Object> person=new HashMap<String,Object>();
  person.put("name","dubbo");
  person.put("age",10);
  RpcInvocation invocation=new RpcInvocation(Constants.$INVOKE,genericInvoke.getParameterTypes(),new Object[]{"getPerson",new String[]{Person.class.getCanonicalName()},new Object[]{person}});
  invocation.setAttachment(Constants.GENERIC_KEY,Constants.GENERIC_SERIALIZATION_NATIVE_JAVA);
  URL url=URL.valueOf("test://test:11/com.alibaba.dubbo.rpc.support.DemoService?" + "accesslog=true&group=dubbo&version=1.1");
  Invoker invoker=Mockito.mock(Invoker.class);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult(new Person("person",10)));
  when(invoker.getUrl()).thenReturn(url);
  when(invoker.getInterface()).thenReturn(DemoService.class);
  genericFilter.invoke(invoker,invocation);
}
