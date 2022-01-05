/** 
 * Test mock invoker selector works as expected
 */
@Test public void testMockedInvokerSelect(){
  initlistsize5();
  invokers.add(mockedInvoker1);
  RpcInvocation mockedInvocation=new RpcInvocation();
  mockedInvocation.setMethodName("sayHello");
  mockedInvocation.setAttachment(Constants.INVOCATION_NEED_MOCK,"true");
  List<Invoker<IHelloService>> mockedInvokers=dic.list(mockedInvocation);
  Assert.assertEquals(1,mockedInvokers.size());
  List<Invoker<IHelloService>> invokers=dic.list(invocation);
  Assert.assertEquals(5,invokers.size());
}
