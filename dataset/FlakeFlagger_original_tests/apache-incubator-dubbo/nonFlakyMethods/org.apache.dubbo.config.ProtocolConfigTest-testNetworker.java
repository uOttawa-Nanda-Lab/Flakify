@Test public void testNetworker() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setNetworker("networker");
  assertThat(protocol.getNetworker(),equalTo("networker"));
}
