@Test public void testMockInvokerFromOverride_Invoke_force_throwCustemException() throws Throwable {
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getBoolean2.mock","force:throw org.apache.dubbo.rpc.cluster.support.wrapper.MyMockException").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getBoolean2");
  try {
    cluster.invoke(invocation).recreate();
    Assert.fail();
  }
 catch (  MyMockException e) {
  }
}
