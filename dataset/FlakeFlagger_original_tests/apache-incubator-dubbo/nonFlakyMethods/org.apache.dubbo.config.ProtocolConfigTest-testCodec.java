@Test public void testCodec() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setName("dubbo");
  protocol.setCodec("mockcodec");
  assertThat(protocol.getCodec(),equalTo("mockcodec"));
}
