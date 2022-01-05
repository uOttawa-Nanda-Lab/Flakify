@Test public void testTelnet() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setTelnet("mocktelnethandler");
  assertThat(protocol.getTelnet(),equalTo("mocktelnethandler"));
}
