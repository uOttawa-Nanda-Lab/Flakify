@Test public void testForName3() throws Exception {
  ClassLoader classLoader=Mockito.mock(ClassLoader.class);
  forName("a.b.c.D",classLoader);
  verify(classLoader).loadClass("a.b.c.D");
}
