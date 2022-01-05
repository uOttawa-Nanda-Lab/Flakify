@Test(expected=MojoExecutionException.class) public void testResourceWithErrors() throws Exception {
  mojo.setTargetGroups("invalid");
  mojo.execute();
}
