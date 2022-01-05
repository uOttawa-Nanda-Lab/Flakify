@Test public void testName() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setName("name");
  Map<String,String> parameters=new HashMap<String,String>();
  ProtocolConfig.appendParameters(parameters,protocol);
  assertThat(protocol.getName(),equalTo("name"));
  assertThat(protocol.getId(),equalTo("name"));
  assertThat(parameters.isEmpty(),is(true));
}
