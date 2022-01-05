@Test public void should_fail_meaningfully_if_method_does_not_return_value() throws Exception {
  thrown.expectIllegalArgumentException("Method 'saveTheDay' in class SuperHero.class has to return a value!");
  MethodSupport.methodResultFor(batman,"saveTheDay");
}
