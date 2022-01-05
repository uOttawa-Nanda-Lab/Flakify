@Test public void authorizationResponseCachedWithSMaxAge() throws Exception {
  assertAuthorizationRequestFullyCached(new MockResponse().addHeader("Cache-Control: s-maxage=60"));
}
