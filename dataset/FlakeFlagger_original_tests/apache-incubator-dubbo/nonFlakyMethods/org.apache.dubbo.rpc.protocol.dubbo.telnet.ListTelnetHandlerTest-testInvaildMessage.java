@Test public void testInvaildMessage() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  String result=list.telnet(mockChannel,"xx");
  assertEquals("No such service xx",result);
}
