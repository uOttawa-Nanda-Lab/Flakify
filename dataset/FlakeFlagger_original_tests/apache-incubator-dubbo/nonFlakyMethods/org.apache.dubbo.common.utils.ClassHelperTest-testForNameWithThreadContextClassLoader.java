@Test public void testForNameWithThreadContextClassLoader() throws Exception {
  ClassLoader oldClassLoader=Thread.currentThread().getContextClassLoader();
  try {
    ClassLoader classLoader=Mockito.mock(ClassLoader.class);
    Thread.currentThread().setContextClassLoader(classLoader);
    ClassHelper.forNameWithThreadContextClassLoader("a.b.c.D");
    verify(classLoader).loadClass("a.b.c.D");
  }
  finally {
    Thread.currentThread().setContextClassLoader(oldClassLoader);
  }
}
