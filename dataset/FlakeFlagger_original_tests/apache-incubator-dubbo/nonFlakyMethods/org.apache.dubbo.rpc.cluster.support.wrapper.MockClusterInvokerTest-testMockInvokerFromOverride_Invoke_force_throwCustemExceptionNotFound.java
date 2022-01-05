@Test public void testMockInvokerFromOverride_Invoke_force_throwCustemExceptionNotFound(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getBoolean2.mock","force:throw java.lang.RuntimeException2").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getBoolean2");
  try {
    cluster.invoke(invocation);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e.getCause() instanceof IllegalStateException);
  }
}
