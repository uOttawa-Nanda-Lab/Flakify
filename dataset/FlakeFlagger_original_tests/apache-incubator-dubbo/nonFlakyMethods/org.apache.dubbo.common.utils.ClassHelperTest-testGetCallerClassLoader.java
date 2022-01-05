@Test public void testGetCallerClassLoader() throws Exception {
  assertThat(getCallerClassLoader(ClassHelperTest.class),sameInstance(ClassHelperTest.class.getClassLoader()));
}
