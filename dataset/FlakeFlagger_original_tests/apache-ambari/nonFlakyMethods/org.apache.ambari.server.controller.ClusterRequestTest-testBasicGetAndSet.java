@Test public void testBasicGetAndSet(){
  Long clusterId=new Long(10);
  String clusterName="foo";
  StackId stackVersion=new StackId("HDP-1.0.1");
  Set<String> hostNames=new HashSet<String>();
  hostNames.add("h1");
  ClusterRequest r1=new ClusterRequest(clusterId,clusterName,stackVersion.getStackId(),hostNames);
  Assert.assertEquals(clusterId,r1.getClusterId());
  Assert.assertEquals(clusterName,r1.getClusterName());
  Assert.assertEquals(stackVersion.getStackId(),r1.getStackVersion());
  Assert.assertArrayEquals(hostNames.toArray(),r1.getHostNames().toArray());
  r1.setClusterId(++clusterId);
  r1.setHostNames(hostNames);
  r1.setClusterName("foo1");
  r1.setStackVersion("HDP-1.0.2");
  hostNames.add("h2");
  Assert.assertEquals(clusterId,r1.getClusterId());
  Assert.assertEquals("foo1",r1.getClusterName());
  Assert.assertEquals("HDP-1.0.2",r1.getStackVersion());
  Assert.assertArrayEquals(hostNames.toArray(),r1.getHostNames().toArray());
}
