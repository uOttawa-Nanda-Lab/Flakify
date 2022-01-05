@Test public void testGetClustersWithFilters() throws AmbariException {
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  clusters.addCluster("c3");
  clusters.addCluster("c4");
  Cluster c1=clusters.getCluster("c1");
  Cluster c2=clusters.getCluster("c2");
  Cluster c3=clusters.getCluster("c3");
  Cluster c4=clusters.getCluster("c4");
  c1.setDesiredStackVersion(new StackId("HDP-0.1"));
  c2.setDesiredStackVersion(new StackId("HDP-0.1"));
  c3.setDesiredStackVersion(new StackId("HDP-1.1.0"));
  ClusterRequest r=new ClusterRequest(null,null,null,null);
  Set<ClusterResponse> resp=controller.getClusters(Collections.singleton(r));
  Assert.assertEquals(4,resp.size());
  r=new ClusterRequest(null,"c1",null,null);
  resp=controller.getClusters(Collections.singleton(r));
  Assert.assertEquals(1,resp.size());
  Assert.assertEquals(c1.getClusterId(),resp.iterator().next().getClusterId().longValue());
  r=new ClusterRequest(null,null,"HDP-0.1",null);
  resp=controller.getClusters(Collections.singleton(r));
  Assert.assertEquals(2,resp.size());
  r=new ClusterRequest(null,null,"",null);
  resp=controller.getClusters(Collections.singleton(r));
  Assert.assertEquals(1,resp.size());
  Assert.assertEquals(c4.getClusterId(),resp.iterator().next().getClusterId().longValue());
}
