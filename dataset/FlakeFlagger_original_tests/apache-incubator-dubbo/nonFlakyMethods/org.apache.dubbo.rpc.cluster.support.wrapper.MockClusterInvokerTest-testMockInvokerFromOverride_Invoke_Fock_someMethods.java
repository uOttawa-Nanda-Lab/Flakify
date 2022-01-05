/** 
 * Test if mock policy works fine: fail-mock
 */
@Test public void testMockInvokerFromOverride_Invoke_Fock_someMethods(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName()).addParameter("getSomething.mock","fail:return x").addParameter("getSomething2.mock","force:return y");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getSomething");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals("something",ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("getSomething2");
  ret=cluster.invoke(invocation);
  Assert.assertEquals("y",ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("getSomething3");
  ret=cluster.invoke(invocation);
  Assert.assertEquals("something3",ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("sayHello");
  ret=cluster.invoke(invocation);
  Assert.assertEquals(null,ret.getValue());
}
