@Test public void testHost() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setHost("host");
  Map<String,String> parameters=new HashMap<String,String>();
  ProtocolConfig.appendParameters(parameters,protocol);
  assertThat(protocol.getHost(),equalTo("host"));
  assertThat(parameters.isEmpty(),is(true));
}
