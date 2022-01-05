@Test public void testValidAppFactoryClassNameIsSet() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.managerFactoryClassName.name())).thenReturn(BaseWroManagerFactory.class.getName());
  victim.init(mockFilterConfig);
}
