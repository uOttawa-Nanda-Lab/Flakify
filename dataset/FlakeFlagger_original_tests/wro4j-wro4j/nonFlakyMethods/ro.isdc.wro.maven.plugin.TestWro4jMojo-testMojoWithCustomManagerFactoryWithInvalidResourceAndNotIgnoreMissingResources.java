@Test(expected=MojoExecutionException.class) public void testMojoWithCustomManagerFactoryWithInvalidResourceAndNotIgnoreMissingResources() throws Exception {
  setWroWithInvalidResources();
  victim.setIgnoreMissingResources(false);
  victim.setWroManagerFactory(CustomManagerFactory.class.getName());
  victim.execute();
}
