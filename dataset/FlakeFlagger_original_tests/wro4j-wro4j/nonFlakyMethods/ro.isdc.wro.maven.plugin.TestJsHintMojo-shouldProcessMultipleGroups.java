@Test public void shouldProcessMultipleGroups() throws Exception {
  getMojo().setTargetGroups("undef,valid,g3");
  getMojo().execute();
}
