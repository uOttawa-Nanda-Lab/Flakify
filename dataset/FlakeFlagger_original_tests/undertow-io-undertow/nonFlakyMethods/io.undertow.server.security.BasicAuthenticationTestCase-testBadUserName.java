@Test public void testBadUserName() throws Exception {
  setAuthenticationChain();
  _testBadUserName();
  assertSingleNotificationType(EventType.FAILED_AUTHENTICATION);
}
