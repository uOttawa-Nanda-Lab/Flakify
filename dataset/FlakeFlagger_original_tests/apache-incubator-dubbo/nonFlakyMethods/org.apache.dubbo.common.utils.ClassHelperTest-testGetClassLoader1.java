@Test public void testGetClassLoader1() throws Exception {
  ClassLoader oldClassLoader=Thread.currentThread().getContextClassLoader();
  try {
    assertThat(getClassLoader(ClassHelperTest.class),sameInstance(oldClassLoader));
    Thread.currentThread().setContextClassLoader(null);
    assertThat(getClassLoader(ClassHelperTest.class),sameInstance(ClassHelperTest.class.getClassLoader()));
  }
  finally {
    Thread.currentThread().setContextClassLoader(oldClassLoader);
  }
}
