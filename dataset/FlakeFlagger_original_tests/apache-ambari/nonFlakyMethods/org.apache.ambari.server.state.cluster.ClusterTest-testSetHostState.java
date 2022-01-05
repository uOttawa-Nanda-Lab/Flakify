@Test public void testSetHostState() throws AmbariException {
  clusters.getHost("h1").setState(HostState.HEARTBEAT_LOST);
  Assert.assertEquals(HostState.HEARTBEAT_LOST,clusters.getHost("h1").getState());
}
