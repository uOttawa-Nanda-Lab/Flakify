@Test(expected=MojoExecutionException.class) public void shouldFailWhenInvalidResourcesAreUsed() throws Exception {
  victim.setIgnoreMissingResources(false);
  victim.execute();
}
