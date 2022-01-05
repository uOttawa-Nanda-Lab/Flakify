@Test public void testGetExtension() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  assertNotNull(draft_6455.getExtension());
  assert (draft_6455.getExtension() instanceof DefaultExtension);
}
