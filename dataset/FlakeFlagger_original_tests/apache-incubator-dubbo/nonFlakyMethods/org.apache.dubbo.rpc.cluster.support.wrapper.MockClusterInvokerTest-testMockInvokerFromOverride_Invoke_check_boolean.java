@Test public void testMockInvokerFromOverride_Invoke_check_boolean(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getBoolean1.mock","force:return true").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getBoolean1");
  Result ret=cluster.invoke(invocation);
  Assert.assertTrue("result type must be Boolean but was : " + ret.getValue().getClass(),ret.getValue() instanceof Boolean);
  Assert.assertEquals(true,Boolean.parseBoolean(ret.getValue().toString()));
}
