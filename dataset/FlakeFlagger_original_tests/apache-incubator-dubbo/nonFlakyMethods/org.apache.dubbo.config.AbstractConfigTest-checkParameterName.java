@Test public void checkParameterName() throws Exception {
  Map<String,String> parameters=Collections.singletonMap("hello",":*,/-._0123abcdABCD");
  try {
    AbstractConfig.checkParameterName(parameters);
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
}
