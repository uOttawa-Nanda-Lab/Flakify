@Test public void shouldUseServletContextLocatorWithPreferredLocatorStrategy(){
  for (  final UriLocator locator : victim.getUriLocators()) {
    if (locator instanceof ServletContextUriLocator) {
      assertEquals(LocatorStrategy.DISPATCHER_FIRST,((ServletContextUriLocator)locator).getLocatorStrategy());
      return;
    }
  }
}
