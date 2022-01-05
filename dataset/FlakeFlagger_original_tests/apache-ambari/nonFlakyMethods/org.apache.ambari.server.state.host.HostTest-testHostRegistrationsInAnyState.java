@Test public void testHostRegistrationsInAnyState() throws Exception {
  clusters.addHost("foo");
  Host host=clusters.getHost("foo");
  host.setIPv4("ipv4");
  host.setIPv6("ipv6");
  long counter=0;
  registerHost(host);
  ensureHostUpdatesReceived(host);
  registerHost(host,false);
  ensureHostUpdatesReceived(host);
  sendHealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.HEALTHY);
  registerHost(host,false);
  ensureHostUpdatesReceived(host);
  sendUnhealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.UNHEALTHY);
  registerHost(host,false);
  ensureHostUpdatesReceived(host);
  timeoutHost(host);
  verifyHostState(host,HostState.HEARTBEAT_LOST);
  registerHost(host,false);
  ensureHostUpdatesReceived(host);
  host.setState(HostState.INIT);
  registerHost(host,false);
}
