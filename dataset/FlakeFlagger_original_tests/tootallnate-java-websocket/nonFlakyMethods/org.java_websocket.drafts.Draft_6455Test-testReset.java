@Test public void testReset() throws Exception {
  Draft_6455 draft_6455=new Draft_6455(Collections.<IExtension>singletonList(new TestExtension()));
  draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension);
  List<IExtension> extensionList=new ArrayList<IExtension>(draft_6455.getKnownExtensions());
  List<IProtocol> protocolList=new ArrayList<IProtocol>(draft_6455.getKnownProtocols());
  draft_6455.reset();
  assertEquals(new DefaultExtension(),draft_6455.getExtension());
  assertNull(draft_6455.getProtocol());
  assertEquals(extensionList,draft_6455.getKnownExtensions());
  assertEquals(protocolList,draft_6455.getKnownProtocols());
}
