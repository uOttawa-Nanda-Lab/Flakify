@Test public void testConfigPropertiesWithValidPreProcessor(){
  final Properties configProperties=new Properties();
  configProperties.setProperty(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,"cssMin");
  initFactory(mockFilterConfig,configProperties);
  assertEquals(1,processorsFactory.getPreProcessors().size());
  assertEquals(CssMinProcessor.class,getProcessor(processorsFactory.getPreProcessors().iterator().next()).getClass());
}
