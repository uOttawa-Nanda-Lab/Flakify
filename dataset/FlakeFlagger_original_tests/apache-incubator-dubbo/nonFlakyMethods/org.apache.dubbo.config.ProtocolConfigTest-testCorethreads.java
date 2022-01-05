@Test public void testCorethreads() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setCorethreads(10);
  assertThat(protocol.getCorethreads(),is(10));
}
