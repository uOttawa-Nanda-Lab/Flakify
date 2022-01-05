@Test public void testNonArgsMethod(){
  invocation.setMethodName("echo");
  invocation.setParameterTypes(new Class<?>[]{});
  invocation.setArguments(new Object[]{});
  cacheFilter.invoke(invoker,invocation);
  RpcResult rpcResult1=(RpcResult)cacheFilter.invoke(invoker1,invocation);
  RpcResult rpcResult2=(RpcResult)cacheFilter.invoke(invoker2,invocation);
  Assert.assertEquals(rpcResult1.getValue(),rpcResult2.getValue());
  Assert.assertEquals(rpcResult1.getValue(),"value");
}
