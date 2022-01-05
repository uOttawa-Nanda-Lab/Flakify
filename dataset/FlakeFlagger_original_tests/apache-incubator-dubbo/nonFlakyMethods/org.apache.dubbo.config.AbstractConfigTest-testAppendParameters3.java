@Test public void testAppendParameters3() throws Exception {
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractConfig.appendParameters(parameters,null);
  TestCase.assertTrue(parameters.isEmpty());
}
