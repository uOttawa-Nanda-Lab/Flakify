@Test public void testChangePath() throws RemotingException {
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=change.telnet(mockChannel,"demo");
  assertEquals("Used the demo as default.\r\nYou can cancel default service by command: cd /",result);
}
