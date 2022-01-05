@Test(expected=MojoExecutionException.class) public void cannotExecuteWhenInvalidResourcesPresentAndDoNotIgnoreMissingResources() throws Exception {
  setWroWithInvalidResources();
  mojo.setIgnoreMissingResources(false);
  mojo.execute();
}
