@Test public void testAccesslog() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setAccesslog("access.log");
  assertThat(protocol.getAccesslog(),equalTo("access.log"));
}
