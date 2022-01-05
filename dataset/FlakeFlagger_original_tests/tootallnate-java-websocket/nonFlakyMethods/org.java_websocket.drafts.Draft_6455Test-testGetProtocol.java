@Test public void testGetProtocol() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertNull(draft_6455.getProtocol());
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertNull(draft_6455.getProtocol());
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));
  assertNull(draft_6455.getProtocol());
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertNotNull(draft_6455.getProtocol());
}
