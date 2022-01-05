@Test public void testMockInvokerFromOverride_Invoke_force_throw(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getBoolean2.mock","force:throw ").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getBoolean2");
  try {
    cluster.invoke(invocation);
    Assert.fail();
  }
 catch (  RpcException e) {
    Assert.assertFalse("not custem exception",e.isBiz());
  }
}
