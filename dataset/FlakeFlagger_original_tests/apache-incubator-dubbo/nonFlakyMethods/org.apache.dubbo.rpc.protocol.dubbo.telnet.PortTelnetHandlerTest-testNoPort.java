@Test public void testNoPort() throws RemotingException {
  String result=port.telnet(null,"-l 20880");
  assertEquals("No such port 20880",result);
}
