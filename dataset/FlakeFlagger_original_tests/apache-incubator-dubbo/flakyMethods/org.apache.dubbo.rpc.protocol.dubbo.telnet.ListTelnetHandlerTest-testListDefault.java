@SuppressWarnings("unchecked") @Test public void testListDefault() throws RemotingException {
  mockInvoker=mock(Invoker.class);
  given(mockInvoker.getInterface()).willReturn(DemoService.class);
  given(mockInvoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:20885/demo"));
  given(mockInvoker.invoke(any(Invocation.class))).willReturn(new RpcResult("ok"));
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService");
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=list.telnet(mockChannel,"");
  assertEquals("Use default service org.apache.dubbo.rpc.protocol.dubbo.support.DemoService.\r\n\r\n" + methodsName,result);
}
