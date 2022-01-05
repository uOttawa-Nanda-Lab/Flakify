@Test public void testRecursiveWildcard4Resources() throws IOException {
  uriLocator.locate(createUri("ro/isdc/wro/**.cs?"));
}
