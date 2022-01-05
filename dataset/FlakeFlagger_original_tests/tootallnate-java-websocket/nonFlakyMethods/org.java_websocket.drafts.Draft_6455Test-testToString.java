@Test public void testToString() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertEquals("Draft_6455 extension: DefaultExtension",draft_6455.toString());
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertEquals("Draft_6455 extension: DefaultExtension",draft_6455.toString());
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));
  assertEquals("Draft_6455 extension: DefaultExtension",draft_6455.toString());
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertEquals("Draft_6455 extension: DefaultExtension protocol: chat",draft_6455.toString());
  draft_6455=new Draft_6455(Collections.<IExtension>singletonList(new TestExtension()),Collections.<IProtocol>singletonList(new Protocol("chat")));
  assertEquals("Draft_6455 extension: DefaultExtension",draft_6455.toString());
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertEquals("Draft_6455 extension: TestExtension protocol: chat",draft_6455.toString());
}
