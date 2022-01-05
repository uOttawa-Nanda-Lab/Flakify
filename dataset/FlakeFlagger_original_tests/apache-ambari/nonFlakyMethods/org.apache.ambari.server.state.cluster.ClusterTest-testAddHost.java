@Test public void testAddHost() throws AmbariException {
  clusters.addHost("h2");
  try {
    clusters.addHost("h2");
    fail("Duplicate add should fail");
  }
 catch (  AmbariException e) {
  }
}
