@Test public void should_fail_meaningfully_if_method_not_found() throws Exception {
  thrown.expectIllegalArgumentException("Can't find method 'commitCrime' in class SuperHero.class. Make sure public" + " method exists and accepts no arguments!");
  MethodSupport.methodResultFor(batman,"commitCrime");
}
