@Test(expected=MojoExecutionException.class) public void testMojoWithConfigurableWroManagerFactoryWithInvalidPreProcessor() throws Exception {
  setWroWithValidResources();
  final String preProcessors=ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS + "=INVALID";
  FileUtils.write(extraConfigFile,preProcessors);
  victim.setIgnoreMissingResources(true);
  victim.setWroManagerFactory(ConfigurableWroManagerFactory.class.getName());
  victim.execute();
}
