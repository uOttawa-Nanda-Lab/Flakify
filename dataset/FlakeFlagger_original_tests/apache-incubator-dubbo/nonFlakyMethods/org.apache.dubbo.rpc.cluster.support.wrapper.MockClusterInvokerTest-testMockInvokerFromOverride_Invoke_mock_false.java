@Test public void testMockInvokerFromOverride_Invoke_mock_false(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("mock","false").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getBoolean2");
  try {
    cluster.invoke(invocation);
    Assert.fail();
  }
 catch (  RpcException e) {
    Assert.assertTrue(e.isTimeout());
  }
}
