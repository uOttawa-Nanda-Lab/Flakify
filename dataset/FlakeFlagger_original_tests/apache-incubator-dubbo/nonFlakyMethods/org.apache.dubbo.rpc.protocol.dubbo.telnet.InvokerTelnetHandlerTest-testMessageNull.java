@Test public void testMessageNull() throws RemotingException {
  mockChannel=mock(Channel.class);
  given(mockChannel.getAttribute("telnet.service")).willReturn(null);
  String result=invoke.telnet(mockChannel,null);
  assertEquals("Please input method name, eg: \r\ninvoke xxxMethod(1234, \"abcd\", {\"prop\" : \"value\"})\r\ninvoke XxxService.xxxMethod(1234, \"abcd\", {\"prop\" : \"value\"})\r\ninvoke com.xxx.XxxService.xxxMethod(1234, \"abcd\", {\"prop\" : \"value\"})",result);
}
