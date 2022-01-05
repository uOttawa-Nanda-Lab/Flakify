@Test public void testGetClusters() throws AmbariException {
  clusters.addCluster("c1");
  Cluster c1=clusters.getCluster("c1");
  c1.setDesiredStackVersion(new StackId("HDP-0.1"));
  ClusterRequest r=new ClusterRequest(null,null,null,null);
  Set<ClusterResponse> resp=controller.getClusters(Collections.singleton(r));
  Assert.assertEquals(1,resp.size());
  ClusterResponse resp1=resp.iterator().next();
  Assert.assertEquals(c1.getClusterId(),resp1.getClusterId().longValue());
  Assert.assertEquals(c1.getClusterName(),resp1.getClusterName());
  Assert.assertEquals(c1.getDesiredStackVersion().getStackId(),resp1.getDesiredStackVersion());
}
