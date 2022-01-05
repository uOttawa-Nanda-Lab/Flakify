@Test public void testCopyInstance() throws Exception {
  Draft_6455 draft_6455=new Draft_6455(Collections.<IExtension>singletonList(new TestExtension()),Collections.<IProtocol>singletonList(new Protocol("chat")));
  Draft_6455 draftCopy=(Draft_6455)draft_6455.copyInstance();
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  assertNotEquals(draft_6455,draftCopy);
  assertEquals(draft_6455.getKnownProtocols(),draftCopy.getKnownProtocols());
  assertEquals(draft_6455.getKnownExtensions(),draftCopy.getKnownExtensions());
  assertNotEquals(draft_6455.getProtocol(),draftCopy.getProtocol());
  assertNotEquals(draft_6455.getExtension(),draftCopy.getExtension());
}
