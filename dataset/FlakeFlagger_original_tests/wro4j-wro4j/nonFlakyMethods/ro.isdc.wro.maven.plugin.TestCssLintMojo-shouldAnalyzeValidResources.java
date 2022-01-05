@Test public void shouldAnalyzeValidResources() throws Exception {
  mojo.setTargetGroups("valid");
  mojo.execute();
}
