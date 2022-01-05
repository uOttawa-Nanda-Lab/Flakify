/** 
 * Test if mock policy works fine: fail-mock
 */
@Test public void testMockInvokerFromOverride_Invoke_checkCompatible_ImplMock2(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("mock","fail").addParameter("invoke_return_error","true");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getSomething");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals("somethingmock",ret.getValue());
}
