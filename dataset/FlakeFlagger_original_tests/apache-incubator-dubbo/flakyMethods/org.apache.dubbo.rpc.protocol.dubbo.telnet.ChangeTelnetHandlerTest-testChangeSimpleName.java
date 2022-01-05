@Test public void testChangeSimpleName() throws RemotingException {
  DubboProtocol.getDubboProtocol().export(mockInvoker);
  String result=change.telnet(mockChannel,"DemoService");
  assertEquals("Used the DemoService as default.\r\nYou can cancel default service by command: cd /",result);
}
