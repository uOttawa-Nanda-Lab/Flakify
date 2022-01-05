@Test public void testKeepAlive() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setKeepAlive(true);
  assertThat(protocol.getKeepAlive(),is(true));
}
