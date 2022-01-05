@SuppressWarnings("unchecked") @Test public void testInvokeAutoFindMethod() throws RemotingException {
  mockInvoker=mock(Invoker.class);
  given(mockInvoker.getInterface()).willReturn(DemoService.class);
  given(mockInvoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:20883/demo"));
  given(mockInvoker.invoke(any(Invocation.class))).willReturn(new RpcResult("ok"));
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  given(mockChannel.getLocalAddress()).willReturn(NetUtils.toAddress("127.0.0.1:5555"));
  given(mockChannel.getRemoteAddress()).willReturn(NetUtils.toAddress("127.0.0.1:20883"));
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=invoke.telnet(mockChannel,"echo(\"ok\")");
  assertTrue(result.contains("ok"));
}
