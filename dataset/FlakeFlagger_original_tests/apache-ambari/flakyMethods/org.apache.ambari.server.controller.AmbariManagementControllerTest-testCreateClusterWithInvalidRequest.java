@Test public void testCreateClusterWithInvalidRequest(){
  ClusterRequest r=new ClusterRequest(null,null,null,null);
  r.toString();
  try {
    controller.createCluster(r);
    fail("Expected create cluster for invalid request");
  }
 catch (  Exception e) {
  }
  r.setClusterId(new Long(1));
  try {
    controller.createCluster(r);
    fail("Expected create cluster for invalid request");
  }
 catch (  Exception e) {
  }
  r.setClusterId(null);
  r.setClusterName("foo");
  try {
    controller.createCluster(r);
    fail("Expected create cluster for invalid request - no stack version");
  }
 catch (  Exception e) {
  }
}
