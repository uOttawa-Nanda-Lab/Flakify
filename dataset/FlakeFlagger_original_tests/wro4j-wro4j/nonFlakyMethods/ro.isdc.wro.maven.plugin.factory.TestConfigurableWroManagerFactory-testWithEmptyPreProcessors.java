@Test public void testWithEmptyPreProcessors(){
  Mockito.when(mockFilterConfig.getInitParameter(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS)).thenReturn("");
  initFactory(mockFilterConfig);
  assertTrue(processorsFactory.getPreProcessors().isEmpty());
}
