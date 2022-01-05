@Test public void testGetKnownProtocols() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertEquals(1,draft_6455.getKnownProtocols().size());
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>emptyList());
  assertEquals(0,draft_6455.getKnownProtocols().size());
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));
  assertEquals(1,draft_6455.getKnownProtocols().size());
  ArrayList<IProtocol> protocols=new ArrayList<IProtocol>();
  protocols.add(new Protocol("chat"));
  protocols.add(new Protocol("test"));
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),protocols);
  assertEquals(2,draft_6455.getKnownProtocols().size());
}
