@Test(expected=MojoExecutionException.class) public void testResourceWithUndefVariables() throws Exception {
  getMojo().setTargetGroups("undef");
  getMojo().execute();
}
