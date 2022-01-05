@Test public void testSlash() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  String result=count.telnet(mockChannel,"");
  assertEquals("/",result);
}
