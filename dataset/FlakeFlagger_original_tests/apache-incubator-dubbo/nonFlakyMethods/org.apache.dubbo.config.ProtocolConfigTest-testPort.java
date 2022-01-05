@Test public void testPort() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setPort(8080);
  Map<String,String> parameters=new HashMap<String,String>();
  ProtocolConfig.appendParameters(parameters,protocol);
  assertThat(protocol.getPort(),equalTo(8080));
  assertThat(parameters.isEmpty(),is(true));
}
