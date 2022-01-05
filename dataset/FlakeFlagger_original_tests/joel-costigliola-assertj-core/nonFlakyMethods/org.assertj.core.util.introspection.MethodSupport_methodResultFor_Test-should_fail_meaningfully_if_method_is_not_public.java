@Test public void should_fail_meaningfully_if_method_is_not_public() throws Exception {
  thrown.expectIllegalArgumentException("Can't find method 'trueIdentity' in class SuperHero.class. Make sure " + "public method exists and accepts no arguments!");
  MethodSupport.methodResultFor(batman,"trueIdentity");
}
