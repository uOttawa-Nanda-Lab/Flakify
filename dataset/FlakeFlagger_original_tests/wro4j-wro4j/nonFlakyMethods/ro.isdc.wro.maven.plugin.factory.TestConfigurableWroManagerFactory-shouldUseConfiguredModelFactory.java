@Test public void shouldUseConfiguredModelFactory(){
  final Properties props=createProperties(ConfigurableModelFactory.KEY,XmlModelFactory.ALIAS);
  initFactory(mockFilterConfig,props);
  final ConfigurableModelFactory configurableModelFactory=(ConfigurableModelFactory)AbstractDecorator.getOriginalDecoratedObject(victim.create().getModelFactory());
  assertEquals(XmlModelFactory.class,configurableModelFactory.getConfiguredStrategy().getClass());
}
