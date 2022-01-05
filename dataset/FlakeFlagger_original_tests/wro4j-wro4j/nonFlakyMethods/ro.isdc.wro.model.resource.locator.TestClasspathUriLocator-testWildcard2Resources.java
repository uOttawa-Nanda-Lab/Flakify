@Test public void testWildcard2Resources() throws IOException {
  uriLocator.locate(createUri("ro/isdc/wro/http/*.cs?"));
}
