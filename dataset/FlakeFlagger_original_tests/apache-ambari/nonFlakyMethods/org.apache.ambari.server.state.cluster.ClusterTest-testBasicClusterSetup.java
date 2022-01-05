@Test public void testBasicClusterSetup() throws AmbariException {
  String clusterName="c2";
  try {
    clusters.getCluster(clusterName);
    fail("Exception expected for invalid cluster");
  }
 catch (  Exception e) {
  }
  clusters.addCluster(clusterName);
  Cluster c2=clusters.getCluster(clusterName);
  Assert.assertNotNull(c2);
  Assert.assertEquals(clusterName,c2.getClusterName());
  c2.setClusterName("foo2");
  Assert.assertEquals("foo2",c2.getClusterName());
  Assert.assertNotNull(c2.getDesiredStackVersion());
  Assert.assertEquals("",c2.getDesiredStackVersion().getStackId());
  StackId stackVersion=new StackId("HDP-1.0");
  c2.setDesiredStackVersion(stackVersion);
  Assert.assertEquals("HDP-1.0",c2.getDesiredStackVersion().getStackId());
}
