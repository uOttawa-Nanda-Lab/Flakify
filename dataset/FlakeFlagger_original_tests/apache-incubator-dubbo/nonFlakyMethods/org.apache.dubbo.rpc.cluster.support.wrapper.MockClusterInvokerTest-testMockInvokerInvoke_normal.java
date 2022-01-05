/** 
 * Test if mock policy works fine: fail-mock
 */
@Test public void testMockInvokerInvoke_normal(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName());
  url=url.addParameter(Constants.MOCK_KEY,"fail");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  URL mockUrl=URL.valueOf("mock://localhost/" + IHelloService.class.getName() + "?getSomething.mock=return aa");
  Protocol protocol=new MockProtocol();
  Invoker<IHelloService> mInvoker1=protocol.refer(IHelloService.class,mockUrl);
  invokers.add(mInvoker1);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getSomething");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals("something",ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("sayHello");
  ret=cluster.invoke(invocation);
  Assert.assertEquals(null,ret.getValue());
}
