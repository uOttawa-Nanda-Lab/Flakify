/** 
 * Test if mock policy works fine: force-mock
 */
@Test public void testMockInvokerInvoke_forcemock(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName());
  url=url.addParameter(Constants.MOCK_KEY,"force:return null");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  URL mockUrl=URL.valueOf("mock://localhost/" + IHelloService.class.getName() + "?getSomething.mock=return aa&getSomething3xx.mock=return xx").addParameters(url.getParameters());
  Protocol protocol=new MockProtocol();
  Invoker<IHelloService> mInvoker1=protocol.refer(IHelloService.class,mockUrl);
  invokers.add(mInvoker1);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("getSomething");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals("aa",ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("getSomething2");
  ret=cluster.invoke(invocation);
  Assert.assertEquals(null,ret.getValue());
  invocation=new RpcInvocation();
  invocation.setMethodName("sayHello");
  ret=cluster.invoke(invocation);
  Assert.assertEquals(null,ret.getValue());
}
