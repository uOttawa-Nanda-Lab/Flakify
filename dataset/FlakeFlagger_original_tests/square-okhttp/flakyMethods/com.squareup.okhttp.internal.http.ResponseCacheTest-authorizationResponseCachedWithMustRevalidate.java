@Test public void authorizationResponseCachedWithMustRevalidate() throws Exception {
  assertAuthorizationRequestFullyCached(new MockResponse().addHeader("Cache-Control: must-revalidate"));
}
