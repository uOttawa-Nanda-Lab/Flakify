@Test public void checkKey() throws Exception {
  try {
    AbstractConfig.checkKey("hello","*,-0123abcdABCD");
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
}
