@Test public void testMojoWithPropertiesSet() throws Exception {
  mojo.setTargetGroups("valid");
  mojo.setIgnoreMissingResources(true);
  mojo.execute();
}
