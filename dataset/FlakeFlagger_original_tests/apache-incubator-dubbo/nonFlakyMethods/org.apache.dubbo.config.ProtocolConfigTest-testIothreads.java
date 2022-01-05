@Test public void testIothreads() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setIothreads(10);
  assertThat(protocol.getIothreads(),is(10));
}
