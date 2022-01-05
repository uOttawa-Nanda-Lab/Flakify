@Test public void testConfigPropertiesWithMultipleValidPostProcessor(){
  final Properties configProperties=new Properties();
  configProperties.setProperty(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS,"jsMin, cssMin");
  initFactory(mockFilterConfig,configProperties);
  assertEquals(2,processorsFactory.getPostProcessors().size());
  assertEquals(JSMinProcessor.class,getProcessor(processorsFactory.getPostProcessors().iterator().next()).getClass());
}
