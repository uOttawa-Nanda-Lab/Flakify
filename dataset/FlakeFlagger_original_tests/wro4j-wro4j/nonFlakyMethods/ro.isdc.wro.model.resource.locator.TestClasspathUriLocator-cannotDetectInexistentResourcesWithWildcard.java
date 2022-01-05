@Test(expected=IOException.class) public void cannotDetectInexistentResourcesWithWildcard() throws IOException {
  uriLocator.locate(createUri("*.NOTEXIST"));
}
