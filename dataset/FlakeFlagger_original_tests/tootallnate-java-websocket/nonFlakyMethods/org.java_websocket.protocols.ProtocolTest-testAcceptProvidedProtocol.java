@Test public void testAcceptProvidedProtocol() throws Exception {
  Protocol protocol0=new Protocol("");
  assertTrue(protocol0.acceptProvidedProtocol(""));
  assertTrue(!protocol0.acceptProvidedProtocol("chat"));
  assertTrue(!protocol0.acceptProvidedProtocol("chat, test"));
  assertTrue(!protocol0.acceptProvidedProtocol("chat, test,"));
  Protocol protocol1=new Protocol("chat");
  assertTrue(protocol1.acceptProvidedProtocol("chat"));
  assertTrue(!protocol1.acceptProvidedProtocol("test"));
  assertTrue(protocol1.acceptProvidedProtocol("chat, test"));
  assertTrue(protocol1.acceptProvidedProtocol("test, chat"));
  assertTrue(protocol1.acceptProvidedProtocol("test,chat"));
  assertTrue(protocol1.acceptProvidedProtocol("chat,test"));
  assertTrue(protocol1.acceptProvidedProtocol("asdchattest,test, chat"));
}
