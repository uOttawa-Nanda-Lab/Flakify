@Test public void testWhenValidPreProcessorsSet(){
  initFactory(mockFilterConfig,createProperties(PARAM_PRE_PROCESSORS,"cssUrlRewriting"));
  assertEquals(1,processorsFactory.getPreProcessors().size());
}
