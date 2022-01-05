@Test public void testException(){
  invocation.setMethodName("echo1");
  invocation.setParameterTypes(new Class<?>[]{String.class});
  invocation.setArguments(new Object[]{"arg2"});
  cacheFilter.invoke(invoker3,invocation);
  RpcResult rpcResult=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult.getValue(),"value2");
}
