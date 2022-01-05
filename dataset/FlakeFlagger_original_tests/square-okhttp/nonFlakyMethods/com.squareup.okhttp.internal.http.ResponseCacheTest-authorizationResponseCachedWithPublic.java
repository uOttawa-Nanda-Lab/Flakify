@Test public void authorizationResponseCachedWithPublic() throws Exception {
  assertAuthorizationRequestFullyCached(new MockResponse().addHeader("Cache-Control: public"));
}
