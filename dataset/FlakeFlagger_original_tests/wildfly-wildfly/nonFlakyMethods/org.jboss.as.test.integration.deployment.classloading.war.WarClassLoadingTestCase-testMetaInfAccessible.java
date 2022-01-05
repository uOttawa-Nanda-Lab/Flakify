@Test public void testMetaInfAccessible() throws ClassNotFoundException {
  ClassLoader cl=Thread.currentThread().getContextClassLoader();
  URL manifestResource=cl.getResource("META-INF/example.txt");
  assertNotNull(manifestResource);
}
