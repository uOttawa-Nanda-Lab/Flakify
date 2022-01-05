@Test public void testCreateClusterWithHostMapping() throws AmbariException {
  Set<String> hostNames=new HashSet<String>();
  hostNames.add("h1");
  hostNames.add("h2");
  ClusterRequest r=new ClusterRequest(null,"c1","HDP-0.1",hostNames);
  try {
    controller.createCluster(r);
    fail("Expected create cluster to fail for invalid hosts");
  }
 catch (  Exception e) {
  }
  try {
    clusters.getCluster("c1");
    fail("Expected to fail for non created cluster");
  }
 catch (  ClusterNotFoundException e) {
  }
  clusters.addHost("h1");
  clusters.addHost("h2");
  clusters.getHost("h1").setOsType("redhat6");
  clusters.getHost("h2").setOsType("redhat6");
  clusters.getHost("h1").persist();
  clusters.getHost("h2").persist();
  controller.createCluster(r);
  Assert.assertNotNull(clusters.getCluster("c1"));
}
