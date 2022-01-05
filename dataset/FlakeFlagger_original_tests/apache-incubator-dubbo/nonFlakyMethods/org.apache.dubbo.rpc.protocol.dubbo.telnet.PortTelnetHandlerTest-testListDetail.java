@Test public void testListDetail() throws RemotingException {
  String result=port.telnet(null,"-l");
  assertEquals("dubbo://127.0.0.1:20887",result);
}
