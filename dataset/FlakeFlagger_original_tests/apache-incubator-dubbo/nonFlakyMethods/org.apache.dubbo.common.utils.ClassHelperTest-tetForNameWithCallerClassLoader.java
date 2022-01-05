@Test public void tetForNameWithCallerClassLoader() throws Exception {
  Class c=ClassHelper.forNameWithCallerClassLoader(ClassHelper.class.getName(),ClassHelperTest.class);
  assertThat(c == ClassHelper.class,is(true));
}
