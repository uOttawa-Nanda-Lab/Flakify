@Test public void checkMethodName() throws Exception {
  try {
    AbstractConfig.checkMethodName("hello","abcdABCD0123abcd");
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
  try {
    AbstractConfig.checkMethodName("hello","0a");
    TestCase.fail("the value should be illegal.");
  }
 catch (  Exception e) {
  }
}
