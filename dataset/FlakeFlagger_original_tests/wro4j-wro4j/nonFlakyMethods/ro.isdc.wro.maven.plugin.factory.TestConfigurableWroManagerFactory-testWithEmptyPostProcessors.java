@Test public void testWithEmptyPostProcessors(){
  Mockito.when(mockFilterConfig.getInitParameter(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS)).thenReturn("");
  initFactory(mockFilterConfig);
  assertTrue(processorsFactory.getPostProcessors().isEmpty());
}
