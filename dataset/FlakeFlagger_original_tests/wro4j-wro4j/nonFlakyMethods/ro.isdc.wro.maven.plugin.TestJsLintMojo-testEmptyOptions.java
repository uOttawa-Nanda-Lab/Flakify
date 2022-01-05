@Test(expected=MojoExecutionException.class) public void testEmptyOptions() throws Exception {
  getMojo().setOptions("");
  getMojo().setTargetGroups("undef");
  getMojo().execute();
}
