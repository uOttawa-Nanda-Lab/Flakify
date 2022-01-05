@Test public void testInvokeWith$Invoke() throws Exception {
  Method genericInvoke=GenericService.class.getMethods()[0];
  Map<String,Object> person=new HashMap<String,Object>();
  person.put("name","dubbo");
  person.put("age",10);
  RpcInvocation invocation=new RpcInvocation(Constants.$INVOKE,genericInvoke.getParameterTypes(),new Object[]{"getPerson",new String[]{Person.class.getCanonicalName()},new Object[]{person}});
  URL url=URL.valueOf("test://test:11/com.alibaba.dubbo.rpc.support.DemoService?" + "accesslog=true&group=dubbo&version=1.1&generic=true");
  Invoker invoker=Mockito.mock(Invoker.class);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult(new Person("person",10)));
  when(invoker.getUrl()).thenReturn(url);
  genericImplFilter.invoke(invoker,invocation);
  Assert.assertEquals("true",invocation.getAttachment(Constants.GENERIC_KEY));
}
