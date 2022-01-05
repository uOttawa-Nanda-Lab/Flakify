@Test(expected=MojoExecutionException.class) public void testWroXmlWithInvalidResourcesAndIgnoreMissingResourcesTrue() throws Exception {
  setWroWithInvalidResources();
  getMojo().setIgnoreMissingResources(true);
  getMojo().execute();
}
