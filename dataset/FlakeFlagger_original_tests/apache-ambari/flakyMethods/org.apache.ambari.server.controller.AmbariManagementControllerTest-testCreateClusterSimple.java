@Test public void testCreateClusterSimple() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  Set<ClusterResponse> r=controller.getClusters(Collections.singleton(new ClusterRequest(null,clusterName,null,null)));
  Assert.assertEquals(1,r.size());
  ClusterResponse c=r.iterator().next();
  Assert.assertEquals(clusterName,c.getClusterName());
  try {
    createCluster(clusterName);
    fail("Duplicate cluster creation should fail");
  }
 catch (  AmbariException e) {
  }
}
