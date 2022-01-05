@Test public void testMojoWithPropertiesSet() throws Exception {
  getMojo().setIgnoreMissingResources(true);
  getMojo().execute();
}
