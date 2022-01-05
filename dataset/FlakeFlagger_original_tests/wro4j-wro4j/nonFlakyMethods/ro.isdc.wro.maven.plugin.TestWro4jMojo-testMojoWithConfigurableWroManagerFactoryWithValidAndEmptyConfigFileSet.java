@Test public void testMojoWithConfigurableWroManagerFactoryWithValidAndEmptyConfigFileSet() throws Exception {
  setWroWithValidResources();
  victim.setIgnoreMissingResources(true);
  victim.setWroManagerFactory(ConfigurableWroManagerFactory.class.getName());
  victim.execute();
}
