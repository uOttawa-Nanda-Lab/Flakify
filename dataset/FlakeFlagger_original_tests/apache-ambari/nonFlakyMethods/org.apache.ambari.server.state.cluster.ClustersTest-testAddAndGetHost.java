@Test public void testAddAndGetHost() throws AmbariException {
  String h1="h1";
  String h2="h2";
  String h3="h3";
  clusters.addHost(h1);
  try {
    clusters.addHost(h1);
    fail("Expected exception on duplicate host entry");
  }
 catch (  Exception e) {
  }
  clusters.addHost(h2);
  clusters.addHost(h3);
  List<Host> hosts=clusters.getHosts();
  Assert.assertEquals(3,hosts.size());
  Assert.assertNotNull(clusters.getHost(h1));
  Assert.assertNotNull(clusters.getHost(h2));
  Assert.assertNotNull(clusters.getHost(h3));
  Host h=clusters.getHost(h2);
  Assert.assertNotNull(h);
  try {
    clusters.getHost("foo");
    fail("Expected error for unknown host");
  }
 catch (  HostNotFoundException e) {
  }
}
