@Test public void testThreads() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setThreads(10);
  assertThat(protocol.getThreads(),is(10));
}
