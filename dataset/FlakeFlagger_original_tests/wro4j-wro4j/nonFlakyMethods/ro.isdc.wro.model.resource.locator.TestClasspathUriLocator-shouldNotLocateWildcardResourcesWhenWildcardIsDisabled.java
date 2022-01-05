@Test(expected=IOException.class) public void shouldNotLocateWildcardResourcesWhenWildcardIsDisabled() throws IOException {
  uriLocator.setEnableWildcards(false).locate(createUri("**.css"));
}
