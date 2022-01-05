@Test public void testHostHeartbeatFlow() throws Exception {
  clusters.addHost("foo");
  Host host=clusters.getHost("foo");
  registerHost(host);
  ensureHostUpdatesReceived(host);
  long counter=0;
  sendHealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.HEALTHY);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  sendHealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.HEALTHY);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.HEALTHY,host.getHealthStatus().getHealthStatus());
  sendUnhealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.UNHEALTHY);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.UNHEALTHY,host.getHealthStatus().getHealthStatus());
  sendUnhealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.UNHEALTHY);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.UNHEALTHY,host.getHealthStatus().getHealthStatus());
  sendHealthyHeartbeat(host,++counter);
  verifyHostState(host,HostState.HEALTHY);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.HEALTHY,host.getHealthStatus().getHealthStatus());
  timeoutHost(host);
  verifyHostState(host,HostState.HEARTBEAT_LOST);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.UNKNOWN,host.getHealthStatus().getHealthStatus());
  timeoutHost(host);
  verifyHostState(host,HostState.HEARTBEAT_LOST);
  Assert.assertEquals(counter,host.getLastHeartbeatTime());
  Assert.assertEquals(HealthStatus.UNKNOWN,host.getHealthStatus().getHealthStatus());
  try {
    sendUnhealthyHeartbeat(host,++counter);
    fail("Invalid event should have triggered an exception");
  }
 catch (  Exception e) {
  }
  verifyHostState(host,HostState.HEARTBEAT_LOST);
  try {
    sendHealthyHeartbeat(host,++counter);
    fail("Invalid event should have triggered an exception");
  }
 catch (  Exception e) {
  }
  verifyHostState(host,HostState.HEARTBEAT_LOST);
}
