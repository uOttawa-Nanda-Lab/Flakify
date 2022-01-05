@Test(expected=CustomException.class) public void shouldOverrideCustomProcessorsFactory() throws Throwable {
  try {
    mojo.setWroManagerFactory(CustomWroManagerFactory.class.getName());
    mojo.setTargetGroups(null);
    mojo.execute();
  }
 catch (  final MojoExecutionException e) {
    throw e.getCause();
  }
}
