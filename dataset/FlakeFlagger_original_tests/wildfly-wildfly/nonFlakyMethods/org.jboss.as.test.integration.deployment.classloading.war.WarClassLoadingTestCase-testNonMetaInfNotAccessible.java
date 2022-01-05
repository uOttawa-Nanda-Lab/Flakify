@Test public void testNonMetaInfNotAccessible() throws ClassNotFoundException {
  ClassLoader cl=Thread.currentThread().getContextClassLoader();
  URL nonManifestResource=cl.getResource("example2.txt");
  assertNull(nonManifestResource);
}
