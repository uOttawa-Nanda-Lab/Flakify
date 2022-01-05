@Test public void testParameters() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setParameters(Collections.singletonMap("k1","v1"));
  assertThat(protocol.getParameters(),hasEntry("k1","v1"));
}
