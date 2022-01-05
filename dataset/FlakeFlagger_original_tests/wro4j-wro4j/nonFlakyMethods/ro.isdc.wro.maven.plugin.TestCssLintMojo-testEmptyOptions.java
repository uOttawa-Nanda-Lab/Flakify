@Test public void testEmptyOptions() throws Exception {
  mojo.setOptions("");
  mojo.setTargetGroups("undef");
  mojo.execute();
}
