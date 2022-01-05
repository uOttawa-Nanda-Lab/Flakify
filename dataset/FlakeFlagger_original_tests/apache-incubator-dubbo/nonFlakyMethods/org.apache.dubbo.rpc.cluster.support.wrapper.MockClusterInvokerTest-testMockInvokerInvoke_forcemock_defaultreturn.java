@Test public void testMockInvokerInvoke_forcemock_defaultreturn(){
  URL url=URL.valueOf("remote://1.2.3.4/" + IHelloService.class.getName());
  url=url.addParameter(Constants.MOCK_KEY,"force");
  Invoker<IHelloService> cluster=getClusterInvoker(url);
  URL mockUrl=URL.valueOf("mock://localhost/" + IHelloService.class.getName() + "?getSomething.mock=return aa&getSomething3xx.mock=return xx&sayHello.mock=return ").addParameters(url.getParameters());
  Protocol protocol=new MockProtocol();
  Invoker<IHelloService> mInvoker1=protocol.refer(IHelloService.class,mockUrl);
  invokers.add(mInvoker1);
  RpcInvocation invocation=new RpcInvocation();
  invocation.setMethodName("sayHello");
  Result ret=cluster.invoke(invocation);
  Assert.assertEquals(null,ret.getValue());
}
