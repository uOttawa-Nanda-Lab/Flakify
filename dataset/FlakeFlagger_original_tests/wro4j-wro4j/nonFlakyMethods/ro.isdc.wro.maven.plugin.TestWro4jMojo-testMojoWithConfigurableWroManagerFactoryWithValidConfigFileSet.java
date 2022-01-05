@Test public void testMojoWithConfigurableWroManagerFactoryWithValidConfigFileSet() throws Exception {
  setWroWithValidResources();
  final String preProcessors=ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS + "=cssMin";
  FileUtils.write(extraConfigFile,preProcessors);
  victim.setIgnoreMissingResources(true);
  victim.setWroManagerFactory(ConfigurableWroManagerFactory.class.getName());
  victim.execute();
}
