@Test public void testHostFSMInit() throws AmbariException {
  clusters.addHost("foo");
  Host host=clusters.getHost("foo");
  verifyHostState(host,HostState.INIT);
}
