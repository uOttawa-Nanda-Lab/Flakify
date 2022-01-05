@Test public void shouldLocateRecursiveWildcardResources() throws IOException {
  uriLocator.locate(createUri("ro/isdc/wro/http/**.css"));
}
