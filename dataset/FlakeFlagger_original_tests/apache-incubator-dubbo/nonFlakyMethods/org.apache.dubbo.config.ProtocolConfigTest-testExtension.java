@Test public void testExtension() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setExtension("extension");
  assertThat(protocol.getExtension(),equalTo("extension"));
}
