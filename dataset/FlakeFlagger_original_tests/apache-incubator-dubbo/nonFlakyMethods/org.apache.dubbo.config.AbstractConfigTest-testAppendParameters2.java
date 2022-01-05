@Test(expected=IllegalStateException.class) public void testAppendParameters2() throws Exception {
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractConfig.appendParameters(parameters,new ParameterConfig());
}
