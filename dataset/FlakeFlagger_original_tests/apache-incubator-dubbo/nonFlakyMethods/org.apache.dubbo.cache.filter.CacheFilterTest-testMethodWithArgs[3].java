@Test public void testMethodWithArgs(){
  invocation.setMethodName("echo1");
  invocation.setParameterTypes(new Class<?>[]{String.class});
  invocation.setArguments(new Object[]{"arg1"});
  cacheFilter.invoke(invoker,invocation);
  RpcResult rpcResult1=(RpcResult)cacheFilter.invoke(invoker1,invocation);
  RpcResult rpcResult2=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult1.getValue(),rpcResult2.getValue());
  Assert.assertEquals(rpcResult1.getValue(),"value");
}
