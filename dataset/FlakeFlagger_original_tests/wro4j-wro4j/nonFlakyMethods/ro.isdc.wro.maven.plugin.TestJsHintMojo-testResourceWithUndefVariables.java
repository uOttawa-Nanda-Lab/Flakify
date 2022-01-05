@Test public void testResourceWithUndefVariables() throws Exception {
  getMojo().setTargetGroups("undef");
  getMojo().execute();
}
