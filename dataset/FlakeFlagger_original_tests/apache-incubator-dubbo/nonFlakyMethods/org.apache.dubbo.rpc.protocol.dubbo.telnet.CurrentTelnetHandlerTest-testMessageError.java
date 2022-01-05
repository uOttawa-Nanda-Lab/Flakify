@Test public void testMessageError() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  String result=count.telnet(mockChannel,"test");
  assertEquals("Unsupported parameter test for pwd.",result);
}
