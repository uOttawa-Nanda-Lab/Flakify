@Test public void testExchanger() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setExchanger("mockexchanger");
  assertThat(protocol.getExchanger(),equalTo("mockexchanger"));
}
