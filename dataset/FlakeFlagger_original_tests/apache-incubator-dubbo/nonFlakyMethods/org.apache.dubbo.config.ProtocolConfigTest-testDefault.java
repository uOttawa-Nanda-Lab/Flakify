@Test public void testDefault() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setDefault(true);
  assertThat(protocol.isDefault(),is(true));
}
