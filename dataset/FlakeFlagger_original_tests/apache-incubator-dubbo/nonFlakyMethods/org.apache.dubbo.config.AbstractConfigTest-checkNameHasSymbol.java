@Test public void checkNameHasSymbol() throws Exception {
  try {
    AbstractConfig.checkNameHasSymbol("hello",":*,/-0123abcdABCD");
  }
 catch (  Exception e) {
    TestCase.fail("the value should be legal.");
  }
}
