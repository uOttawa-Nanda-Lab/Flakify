@Test public void testWildcard1Resources() throws IOException {
  WroTestUtils.compare(uriLocator.locate(createUri("ro/isdc/wro/http/*.merged")),uriLocator.locate(createUri("ro/isdc/wro/http/*.css")));
}
