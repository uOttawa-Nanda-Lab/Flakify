@Test public void shouldNotFailWhenAnalyzeInvalidResources() throws Exception {
  mojo.setFailNever(true);
  mojo.setTargetGroups("invalidResources");
  mojo.execute();
}
