@Test(expected=WroRuntimeException.class) public void cannotAcceptInvalidAppFactoryClassNameIsSet() throws Exception {
  victim=new WroFilter();
  when(mockFilterConfig.getInitParameter(ConfigConstants.managerFactoryClassName.name())).thenReturn("Invalid value");
  victim.init(mockFilterConfig);
}
