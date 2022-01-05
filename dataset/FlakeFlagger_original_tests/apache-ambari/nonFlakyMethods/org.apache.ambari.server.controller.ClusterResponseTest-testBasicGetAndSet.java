@Test public void testBasicGetAndSet(){
  Long clusterId=new Long(10);
  String clusterName="foo";
  Set<String> hostNames=new HashSet<String>();
  hostNames.add("h1");
  ClusterResponse r1=new ClusterResponse(clusterId,clusterName,hostNames,"bar");
  Assert.assertEquals(clusterId,r1.getClusterId());
  Assert.assertEquals(clusterName,r1.getClusterName());
  Assert.assertArrayEquals(hostNames.toArray(),r1.getHostNames().toArray());
  Assert.assertEquals("bar",r1.getDesiredStackVersion());
}
