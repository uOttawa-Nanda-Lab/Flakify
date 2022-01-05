@Test public void testErrorsWithNoFailFast() throws Exception {
  mojo.setFailNever(true);
  mojo.setOptions("undef, browser");
  mojo.setTargetGroups("undef");
  mojo.execute();
}
