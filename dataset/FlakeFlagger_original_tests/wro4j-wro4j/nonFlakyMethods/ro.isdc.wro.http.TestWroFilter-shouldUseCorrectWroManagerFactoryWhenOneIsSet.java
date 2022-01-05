/** 
 * Test that when setting WwroManagerFactory via setter, even if wroConfiguration has a different {@link WroManagerFactory} configured, the first one instance is used.
 */
@Test public void shouldUseCorrectWroManagerFactoryWhenOneIsSet() throws Exception {
  final Class<?> managerClass=TestWroManagerFactory.class;
  victim.setWroManagerFactory(null);
  when(mockFilterConfig.getInitParameter(ConfigConstants.managerFactoryClassName.name())).thenReturn(managerClass.getName());
  victim.init(mockFilterConfig);
  final Class<?> actualClass=((DefaultWroManagerFactory)AbstractDecorator.getOriginalDecoratedObject(victim.getWroManagerFactory())).getFactory().getClass();
  Assert.assertSame(managerClass,actualClass);
}
