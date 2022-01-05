@Test public void testGetCloseHandshakeType() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertEquals(CloseHandshakeType.TWOWAY,draft_6455.getCloseHandshakeType());
}
