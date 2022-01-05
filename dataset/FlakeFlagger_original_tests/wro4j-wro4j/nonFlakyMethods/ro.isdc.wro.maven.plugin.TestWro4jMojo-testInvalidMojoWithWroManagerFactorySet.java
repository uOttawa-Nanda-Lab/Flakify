@Test(expected=MojoExecutionException.class) public void testInvalidMojoWithWroManagerFactorySet() throws Exception {
  victim.setWroManagerFactory("INVALID_CLASS_NAME");
  victim.execute();
}
