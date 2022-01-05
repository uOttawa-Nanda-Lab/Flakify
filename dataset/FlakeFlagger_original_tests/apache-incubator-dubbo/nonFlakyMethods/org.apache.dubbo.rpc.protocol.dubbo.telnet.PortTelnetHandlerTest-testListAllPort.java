@Test public void testListAllPort() throws RemotingException {
  String result=port.telnet(null,"");
  assertEquals("20887",result);
}
