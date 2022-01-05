@Test public void shouldUseInitiallySetManagerEvenIfAnInvalidAppFactoryClassNameIsSet() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.managerFactoryClassName.name())).thenReturn("Invalid value");
  victim.init(mockFilterConfig);
  Assert.assertSame(mockManagerFactory,AbstractDecorator.getOriginalDecoratedObject(victim.getWroManagerFactory()));
}
