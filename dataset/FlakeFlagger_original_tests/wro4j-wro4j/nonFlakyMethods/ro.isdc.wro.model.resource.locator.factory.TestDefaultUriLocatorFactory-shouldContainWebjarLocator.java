@Test public void shouldContainWebjarLocator(){
  boolean hasWebjarLocator=false;
  for (  final UriLocator locator : victim.getUriLocators()) {
    if (locator instanceof WebjarUriLocator) {
      hasWebjarLocator=true;
    }
  }
  assertTrue(hasWebjarLocator);
}
