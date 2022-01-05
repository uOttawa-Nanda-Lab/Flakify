@Test public void testMockInvokerFromOverride_Invoke_check_String(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getSomething.mock","force:return 1688").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getSomething");
  Result ret=cluster.invoke(invocation);
  Assert.assertTrue("result type must be String but was : " + ret.getValue().getClass(),ret.getValue() instanceof String);
  Assert.assertEquals("1688",(String)ret.getValue());
}
