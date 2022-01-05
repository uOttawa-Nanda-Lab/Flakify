@SuppressWarnings("unchecked") @Test public void testListDetail() throws RemotingException {
  int port=NetUtils.getAvailablePort();
  mockInvoker=mock(Invoker.class);
  given(mockInvoker.getInterface()).willReturn(DemoService.class);
  given(mockInvoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:" + port + "/demo"));
  given(mockInvoker.invoke(any(Invocation.class))).willReturn(new RpcResult("ok"));
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=list.telnet(mockChannel,"-l");
  assertEquals("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService -> dubbo://127.0.0.1:" + port + "/demo",result);
}
