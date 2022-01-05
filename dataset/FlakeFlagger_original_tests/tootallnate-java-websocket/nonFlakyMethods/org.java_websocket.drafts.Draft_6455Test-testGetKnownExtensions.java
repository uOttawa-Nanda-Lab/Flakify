@Test public void testGetKnownExtensions() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertEquals(1,draft_6455.getKnownExtensions().size());
  draft_6455=new Draft_6455(new DefaultExtension());
  assertEquals(1,draft_6455.getKnownExtensions().size());
  draft_6455=new Draft_6455(new TestExtension());
  assertEquals(2,draft_6455.getKnownExtensions().size());
}
