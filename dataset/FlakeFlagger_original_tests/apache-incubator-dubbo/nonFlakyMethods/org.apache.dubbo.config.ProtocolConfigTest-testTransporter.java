@Test public void testTransporter() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setTransporter("mocktransporter");
  assertThat(protocol.getTransporter(),equalTo("mocktransporter"));
}
