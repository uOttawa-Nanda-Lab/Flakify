@Test public void testBasicSuccess() throws Exception {
  setAuthenticationChain();
  _testBasicSuccess();
  assertSingleNotificationType(EventType.AUTHENTICATED);
}
