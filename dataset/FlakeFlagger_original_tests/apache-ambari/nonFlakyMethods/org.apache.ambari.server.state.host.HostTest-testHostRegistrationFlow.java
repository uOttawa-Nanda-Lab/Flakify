@Test public void testHostRegistrationFlow() throws Exception {
  clusters.addHost("foo");
  Host host=clusters.getHost("foo");
  registerHost(host);
  verifyHostState(host,HostState.WAITING_FOR_HOST_STATUS_UPDATES);
  boolean exceptionThrown=false;
  try {
    registerHost(host);
  }
 catch (  Exception e) {
    exceptionThrown=true;
  }
  if (!exceptionThrown) {
    fail("Expected invalid transition exception to be thrown");
  }
  ensureHostUpdatesReceived(host);
  verifyHostState(host,HostState.HEALTHY);
  exceptionThrown=false;
  try {
    ensureHostUpdatesReceived(host);
  }
 catch (  Exception e) {
    exceptionThrown=true;
  }
  if (!exceptionThrown) {
    fail("Expected invalid transition exception to be thrown");
  }
}
