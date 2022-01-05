@Test(expected=MojoExecutionException.class) public void testMojoWithPropertiesSet() throws Exception {
  getMojo().setIgnoreMissingResources(true);
  getMojo().execute();
}
