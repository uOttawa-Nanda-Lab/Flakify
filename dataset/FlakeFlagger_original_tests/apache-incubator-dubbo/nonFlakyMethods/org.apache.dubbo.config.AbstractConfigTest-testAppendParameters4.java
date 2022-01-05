@Test public void testAppendParameters4() throws Exception {
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractConfig.appendParameters(parameters,new ParameterConfig(1,"hello/world",30,"password"));
  TestCase.assertEquals("one",parameters.get("key.1"));
  TestCase.assertEquals("two",parameters.get("key.2"));
  TestCase.assertEquals("1",parameters.get("num"));
  TestCase.assertEquals("hello%2Fworld",parameters.get("naming"));
  TestCase.assertEquals("30",parameters.get("age"));
}
