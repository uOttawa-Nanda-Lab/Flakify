@Test public void testInvaildMessage() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  String result=invoke.telnet(mockChannel,"(");
  assertEquals("Invalid parameters, format: service.method(args)",result);
}
