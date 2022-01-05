@Test public void testBadPassword() throws Exception {
  setAuthenticationChain();
  _testBadPassword();
  assertSingleNotificationType(EventType.FAILED_AUTHENTICATION);
}
