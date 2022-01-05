@Test(expected=MojoExecutionException.class) public void testMojoWithWroManagerFactorySet() throws Exception {
  victim.setWroManagerFactory(ExceptionThrowingWroManagerFactory.class.getName());
  victim.execute();
}
