@Test public void testOptimizer() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setOptimizer("optimizer");
  assertThat(protocol.getOptimizer(),equalTo("optimizer"));
}
