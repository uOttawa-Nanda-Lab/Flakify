@Test public void checkMultiName() throws Exception {
  try {
    AbstractConfig.checkMultiName("hello",",-._0123abcdABCD");
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
}
