@Test(expected=WroRuntimeException.class) public void shouldFailWhenAnInvalidWroManagerClassNameIsLoadedFromCustomLocation() throws Exception {
  final Properties props=new Properties();
  props.setProperty(ConfigConstants.managerFactoryClassName.name(),"invalid");
  victim.setProperties(props);
  victim.init(mockFilterConfig);
}
