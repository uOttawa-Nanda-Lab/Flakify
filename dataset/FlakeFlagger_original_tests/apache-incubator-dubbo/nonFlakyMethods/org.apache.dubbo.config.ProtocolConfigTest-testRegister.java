@Test public void testRegister() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setRegister(true);
  assertThat(protocol.isRegister(),is(true));
}
