@Test(expected=MojoExecutionException.class) public void testMojoWithConfigurableWroManagerFactory() throws Exception {
  setWroWithValidResources();
  victim.setIgnoreMissingResources(true);
  victim.setExtraConfigFile(null);
  victim.setWroManagerFactory(ConfigurableWroManagerFactory.class.getName());
  victim.execute();
}
