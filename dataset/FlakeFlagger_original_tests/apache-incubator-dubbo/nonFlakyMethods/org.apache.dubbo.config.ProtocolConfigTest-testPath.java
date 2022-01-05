@Test public void testPath() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setContextpath("context-path");
  Map<String,String> parameters=new HashMap<String,String>();
  ProtocolConfig.appendParameters(parameters,protocol);
  assertThat(protocol.getPath(),equalTo("context-path"));
  assertThat(protocol.getContextpath(),equalTo("context-path"));
  assertThat(parameters.isEmpty(),is(true));
  protocol.setPath("path");
  assertThat(protocol.getPath(),equalTo("path"));
  assertThat(protocol.getContextpath(),equalTo("path"));
}
