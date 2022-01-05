@Test public void testChangeLogLevel() throws RemotingException {
  mockChannel=mock(Channel.class);
  String result=log.telnet(mockChannel,"error");
  assertTrue(result.contains("\r\nCURRENT LOG LEVEL:ERROR"));
  String result2=log.telnet(mockChannel,"warn");
  assertTrue(result2.contains("\r\nCURRENT LOG LEVEL:WARN"));
}
