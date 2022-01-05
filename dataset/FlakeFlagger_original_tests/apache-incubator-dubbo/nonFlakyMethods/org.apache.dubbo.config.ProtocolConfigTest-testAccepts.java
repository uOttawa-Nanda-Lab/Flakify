@Test public void testAccepts() throws Exception {
  ProtocolConfig protocol=new ProtocolConfig();
  protocol.setAccepts(10);
  assertThat(protocol.getAccepts(),is(10));
}
