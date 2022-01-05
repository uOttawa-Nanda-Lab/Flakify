@Test public void testPrintLog() throws RemotingException {
  mockChannel=mock(Channel.class);
  String result=log.telnet(mockChannel,"100");
  assertTrue(result.contains("CURRENT LOG APPENDER"));
}
