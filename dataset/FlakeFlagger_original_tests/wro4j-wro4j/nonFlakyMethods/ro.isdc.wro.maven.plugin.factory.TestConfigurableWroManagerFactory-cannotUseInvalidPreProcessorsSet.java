@Test(expected=WroRuntimeException.class) public void cannotUseInvalidPreProcessorsSet(){
  initFactory(mockFilterConfig,createProperties(PARAM_PRE_PROCESSORS,"INVALID1,INVALID2"));
  processorsFactory.getPreProcessors();
}
