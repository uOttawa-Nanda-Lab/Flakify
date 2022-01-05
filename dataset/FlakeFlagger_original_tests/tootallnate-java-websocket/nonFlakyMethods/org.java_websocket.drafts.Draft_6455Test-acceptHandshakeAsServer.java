@Test public void acceptHandshakeAsServer() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedata));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocol));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataExtension));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension));
  draft_6455=new Draft_6455(new TestExtension());
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedata));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocol));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataExtension));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension));
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat")));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedata));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocol));
  assertEquals(HandshakeState.NOT_MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataExtension));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension));
  ArrayList<IProtocol> protocols=new ArrayList<IProtocol>();
  protocols.add(new Protocol("chat"));
  protocols.add(new Protocol(""));
  draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),protocols);
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedata));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocol));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataExtension));
  assertEquals(HandshakeState.MATCHED,draft_6455.acceptHandshakeAsServer(handshakedataProtocolExtension));
}
