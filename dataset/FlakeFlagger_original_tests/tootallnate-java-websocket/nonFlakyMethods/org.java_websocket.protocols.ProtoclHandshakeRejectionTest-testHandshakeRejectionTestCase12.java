@Test(timeout=5000) public void testHandshakeRejectionTestCase12() throws Exception {
  testProtocolRejection(12,new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>singletonList(new Protocol("chat"))));
}
