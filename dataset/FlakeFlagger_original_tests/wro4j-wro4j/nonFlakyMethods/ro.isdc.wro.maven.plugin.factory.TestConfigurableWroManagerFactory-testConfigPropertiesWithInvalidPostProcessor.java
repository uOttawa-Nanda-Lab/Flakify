@Test(expected=WroRuntimeException.class) public void testConfigPropertiesWithInvalidPostProcessor(){
  final Properties configProperties=new Properties();
  configProperties.setProperty(PARAM_POST_PROCESSORS,"INVALID");
  initFactory(mockFilterConfig,configProperties);
  processorsFactory.getPostProcessors();
}
