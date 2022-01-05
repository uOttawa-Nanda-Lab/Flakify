@Test(expected=MojoExecutionException.class) public void shouldAnalyzeInvalidResources() throws Exception {
  mojo.setTargetGroups("invalidResources");
  mojo.execute();
}
