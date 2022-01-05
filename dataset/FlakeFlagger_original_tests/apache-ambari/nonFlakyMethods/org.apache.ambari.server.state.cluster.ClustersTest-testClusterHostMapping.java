@Test public void testClusterHostMapping() throws AmbariException {
  String c1="c1";
  String c2="c2";
  String h1="h1";
  String h2="h2";
  String h3="h3";
  try {
    clusters.mapHostToCluster(h1,c1);
    fail("Expected exception for invalid cluster/host");
  }
 catch (  Exception e) {
  }
  clusters.addCluster(c1);
  clusters.addCluster(c2);
  clusters.getCluster(c1).setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.getCluster(c2).setDesiredStackVersion(new StackId("HDP-0.1"));
  Assert.assertNotNull(clusters.getCluster(c1));
  Assert.assertNotNull(clusters.getCluster(c2));
  try {
    clusters.mapHostToCluster(h1,c1);
    fail("Expected exception for invalid host");
  }
 catch (  Exception e) {
  }
  clusters.addHost(h1);
  clusters.addHost(h2);
  clusters.addHost(h3);
  Assert.assertNotNull(clusters.getHost(h1));
  clusters.getHost(h1).setOsType("redhat6");
  clusters.getHost(h2).setOsType("centos5");
  clusters.getHost(h3).setOsType("centos6");
  clusters.getHost(h1).persist();
  clusters.getHost(h2).persist();
  clusters.getHost(h3).persist();
  Set<Cluster> c=clusters.getClustersForHost(h3);
  Assert.assertEquals(0,c.size());
  clusters.mapHostToCluster(h1,c1);
  clusters.mapHostToCluster(h2,c1);
  clusters.mapHostToCluster(h1,c1);
  Set<String> hostnames=new HashSet<String>();
  hostnames.add(h1);
  hostnames.add(h2);
  clusters.mapHostsToCluster(hostnames,c2);
  c=clusters.getClustersForHost(h1);
  Assert.assertEquals(2,c.size());
  c=clusters.getClustersForHost(h2);
  Assert.assertEquals(2,c.size());
  Map<String,Host> hostsForC1=clusters.getHostsForCluster(c1);
  Assert.assertEquals(2,hostsForC1.size());
  Assert.assertTrue(hostsForC1.containsKey(h1));
  Assert.assertTrue(hostsForC1.containsKey(h2));
  Assert.assertNotNull(hostsForC1.get(h1));
  Assert.assertNotNull(hostsForC1.get(h2));
}
