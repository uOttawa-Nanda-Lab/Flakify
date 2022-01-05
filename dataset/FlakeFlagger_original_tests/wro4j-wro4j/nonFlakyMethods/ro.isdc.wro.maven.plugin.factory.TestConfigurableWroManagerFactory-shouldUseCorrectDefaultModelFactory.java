@Test public void shouldUseCorrectDefaultModelFactory(){
  initFactory(mockFilterConfig,new Properties());
  final ConfigurableModelFactory configurableModelFactory=(ConfigurableModelFactory)AbstractDecorator.getOriginalDecoratedObject(victim.create().getModelFactory());
  assertEquals(SmartWroModelFactory.class,configurableModelFactory.getConfiguredStrategy().getClass());
}
