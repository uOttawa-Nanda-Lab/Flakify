@Test(expected=MojoExecutionException.class) public void cannotExecuteWhenInvalidResourcesPresentAndDoNotIgnoreMissingResources() throws Exception {
  setWroWithInvalidResources();
  victim.setIgnoreMissingResources(false);
  victim.execute();
}
