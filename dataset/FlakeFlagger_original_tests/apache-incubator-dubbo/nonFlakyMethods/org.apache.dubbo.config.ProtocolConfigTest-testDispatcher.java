@Test public void testDispatcher() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setDispatcher("mockdispatcher");
  assertThat(protocol.getDispatcher(),equalTo("mockdispatcher"));
}
