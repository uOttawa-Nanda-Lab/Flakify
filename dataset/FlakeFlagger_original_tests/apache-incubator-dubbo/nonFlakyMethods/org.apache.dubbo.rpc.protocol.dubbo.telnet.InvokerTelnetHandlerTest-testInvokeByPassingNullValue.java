@SuppressWarnings("unchecked") @Test public void testInvokeByPassingNullValue() throws RemotingException {
  mockInvoker=mock(Invoker.class);
  given(mockInvoker.getInterface()).willReturn(DemoService.class);
  given(mockInvoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:20883/demo"));
  given(mockInvoker.invoke(any(Invocation.class))).willReturn(new RpcResult("ok"));
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService");
  given(mockChannel.getLocalAddress()).willReturn(NetUtils.toAddress("127.0.0.1:5555"));
  given(mockChannel.getRemoteAddress()).willReturn(NetUtils.toAddress("127.0.0.1:20883"));
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  try {
    invoke.telnet(mockChannel,"DemoService.add(null, 2)");
    fail("It should cause a NullPointerException by the above code.");
  }
 catch (  NullPointerException ex) {
    String message=ex.getMessage();
    assertEquals("The type of No.1 parameter is primitive(int), but the value passed is null.",message);
  }
  try {
    invoke.telnet(mockChannel,"DemoService.add(1, null)");
    fail("It should cause a NullPointerException by the above code.");
  }
 catch (  NullPointerException ex) {
    String message=ex.getMessage();
    assertEquals("The type of No.2 parameter is primitive(long), but the value passed is null.",message);
  }
  try {
    invoke.telnet(mockChannel,"DemoService.sayHello(null)");
  }
 catch (  NullPointerException ex) {
    fail("It shouldn't cause a NullPointerException by the above code.");
  }
}
