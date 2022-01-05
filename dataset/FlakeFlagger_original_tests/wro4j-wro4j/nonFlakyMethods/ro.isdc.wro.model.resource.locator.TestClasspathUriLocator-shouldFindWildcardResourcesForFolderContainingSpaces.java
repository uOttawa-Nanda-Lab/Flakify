@Test public void shouldFindWildcardResourcesForFolderContainingSpaces() throws IOException {
  uriLocator.locate(createUri("test/folder with spaces/**.css"));
}
