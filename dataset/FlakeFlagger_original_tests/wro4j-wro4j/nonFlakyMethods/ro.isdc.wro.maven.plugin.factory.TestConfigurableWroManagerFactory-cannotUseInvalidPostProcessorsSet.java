@Test(expected=WroRuntimeException.class) public void cannotUseInvalidPostProcessorsSet(){
  initFactory(mockFilterConfig,createProperties(PARAM_POST_PROCESSORS,"INVALID1,INVALID2"));
  processorsFactory.getPostProcessors();
}
