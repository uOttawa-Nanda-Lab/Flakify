@Test public void testGetClassLoader2() throws Exception {
  assertThat(getClassLoader(),sameInstance(ClassHelper.class.getClassLoader()));
}
