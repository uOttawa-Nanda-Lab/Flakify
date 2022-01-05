@Test public void testMockInvokerFromOverride_Invoke_check_int(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getInt1.mock","force:return 1688").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getInt1");
  Result ret=cluster.invoke(invocation);
  Assert.assertTrue("result type must be integer but was : " + ret.getValue().getClass(),ret.getValue() instanceof Integer);
  Assert.assertEquals(new Integer(1688),(Integer)ret.getValue());
}
