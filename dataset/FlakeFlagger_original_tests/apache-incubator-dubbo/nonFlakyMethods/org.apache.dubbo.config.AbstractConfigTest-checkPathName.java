@Test public void checkPathName() throws Exception {
  try {
    AbstractConfig.checkPathName("hello","/-$._0123abcdABCD");
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
}
