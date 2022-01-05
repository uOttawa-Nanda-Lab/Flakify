@Test(expected=WroRuntimeException.class) public void testConfigPropertiesWithInvalidPreProcessor(){
  final Properties configProperties=new Properties();
  configProperties.setProperty(PARAM_PRE_PROCESSORS,"INVALID");
  initFactory(mockFilterConfig,configProperties);
  processorsFactory.getPreProcessors();
}
