@Test public void testWhenValidPostProcessorsSet(){
  initFactory(mockFilterConfig,createProperties(PARAM_POST_PROCESSORS,"cssMinJawr, jsMin, cssVariables"));
  assertEquals(3,processorsFactory.getPostProcessors().size());
}
