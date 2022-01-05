@Test(expected=MojoExecutionException.class) public void testResourceWithUndefVariablesAndUndefOption() throws Exception {
  mojo.setOptions("undef, browser");
  mojo.setTargetGroups("undef");
  mojo.execute();
}
