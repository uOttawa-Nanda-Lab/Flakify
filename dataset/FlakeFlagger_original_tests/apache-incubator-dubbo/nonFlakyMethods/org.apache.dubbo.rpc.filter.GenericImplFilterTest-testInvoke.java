@Test public void testInvoke() throws Exception {
  RpcInvocation invocation=new RpcInvocation("getPerson",new Class[]{Person.class},new Object[]{new Person("dubbo",10)});
  URL url=URL.valueOf("test://test:11/com.alibaba.dubbo.rpc.support.DemoService?" + "accesslog=true&group=dubbo&version=1.1&generic=true");
  Invoker invoker=Mockito.mock(Invoker.class);
  Map<String,Object> person=new HashMap<String,Object>();
  person.put("name","dubbo");
  person.put("age",10);
  when(invoker.invoke(any(Invocation.class))).thenReturn(new RpcResult(person));
  when(invoker.getUrl()).thenReturn(url);
  when(invoker.getInterface()).thenReturn(DemoService.class);
  Result result=genericImplFilter.invoke(invoker,invocation);
  Assert.assertEquals(Person.class,result.getValue().getClass());
  Assert.assertEquals(10,((Person)result.getValue()).getAge());
}
