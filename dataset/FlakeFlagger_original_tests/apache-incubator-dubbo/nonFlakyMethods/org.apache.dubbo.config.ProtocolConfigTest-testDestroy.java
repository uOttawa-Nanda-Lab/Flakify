@Test public void testDestroy() throws Exception {
  Protocol protocol=Mockito.mock(Protocol.class);
  MockProtocol2.delegate=protocol;
  ProtocolConfig protocolConfig=new ProtocolConfig();
  protocolConfig.setName("mockprotocol2");
  protocolConfig.destroy();
  Mockito.verify(protocol).destroy();
}
