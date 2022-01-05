@SuppressWarnings("unchecked") @Test public void testList() throws RemotingException {
  mockInvoker=mock(Invoker.class);
  given(mockInvoker.getInterface()).willReturn(DemoService.class);
  given(mockInvoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:20885/demo"));
  given(mockInvoker.invoke(any(Invocation.class))).willReturn(new RpcResult("ok"));
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=list.telnet(mockChannel,"");
  assertEquals("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService",result);
}
