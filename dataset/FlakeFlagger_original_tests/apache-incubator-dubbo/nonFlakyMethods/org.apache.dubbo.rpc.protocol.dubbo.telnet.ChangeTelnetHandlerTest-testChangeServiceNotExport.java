@Test public void testChangeServiceNotExport() throws RemotingException {
  String result=change.telnet(mockChannel,"demo");
  assertEquals("No such service demo",result);
}
