@Test public void testService() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService");
  String result=count.telnet(mockChannel,"");
  assertEquals("org.apache.dubbo.rpc.protocol.dubbo.support.DemoService",result);
}
