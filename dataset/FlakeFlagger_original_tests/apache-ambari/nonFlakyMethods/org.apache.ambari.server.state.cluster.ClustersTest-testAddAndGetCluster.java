@Test public void testAddAndGetCluster() throws AmbariException {
  String c1="foo";
  String c2="foo";
  clusters.addCluster(c1);
  try {
    clusters.addCluster(c1);
    fail("Exception should be thrown on invalid add");
  }
 catch (  AmbariException e) {
  }
  try {
    clusters.addCluster(c2);
    fail("Exception should be thrown on invalid add");
  }
 catch (  AmbariException e) {
  }
  c2="foo2";
  clusters.addCluster(c2);
  Assert.assertNotNull(clusters.getCluster(c1));
  Assert.assertNotNull(clusters.getCluster(c2));
  Assert.assertEquals(c1,clusters.getCluster(c1).getClusterName());
  Assert.assertEquals(c2,clusters.getCluster(c2).getClusterName());
  Map<String,Cluster> verifyClusters=clusters.getClusters();
  Assert.assertTrue(verifyClusters.containsKey(c1));
  Assert.assertTrue(verifyClusters.containsKey(c2));
  Assert.assertNotNull(verifyClusters.get(c1));
  Assert.assertNotNull(verifyClusters.get(c2));
  Cluster c=clusters.getCluster(c1);
  c.setClusterName("foobar");
  long cId=c.getClusterId();
  Cluster changed=clusters.getCluster("foobar");
  Assert.assertNotNull(changed);
  Assert.assertEquals(cId,changed.getClusterId());
  Assert.assertEquals("foobar",clusters.getClusterById(cId).getClusterName());
}
