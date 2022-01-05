@Test public void shouldUseCorrectWroManagerFactoryWhenPropertiesAreLoadedFromCustomLocation() throws Exception {
  final Properties props=new Properties();
  props.setProperty(ConfigConstants.managerFactoryClassName.name(),NoProcessorsWroManagerFactory.class.getName());
  victim.setProperties(props);
  victim.init(mockFilterConfig);
  final WroManagerFactory actual=((DefaultWroManagerFactory)victim.getWroManagerFactory()).getFactory();
  assertEquals(NoProcessorsWroManagerFactory.class,actual.getClass());
}
