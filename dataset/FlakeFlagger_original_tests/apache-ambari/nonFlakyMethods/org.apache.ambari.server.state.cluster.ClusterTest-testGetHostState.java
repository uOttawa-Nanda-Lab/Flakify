@Test public void testGetHostState() throws AmbariException {
  Assert.assertEquals(HostState.INIT,clusters.getHost("h1").getState());
}
