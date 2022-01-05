@Test public void testCreateServiceWithInvalidInfo() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  String serviceName="HDFS";
  try {
    createService(clusterName,serviceName,State.INSTALLING);
    fail("Service creation should fail for invalid state");
  }
 catch (  Exception e) {
  }
  try {
    clusters.getCluster(clusterName).getService(serviceName);
    fail("Service creation should have failed");
  }
 catch (  Exception e) {
  }
  try {
    createService(clusterName,serviceName,State.INSTALLED);
    fail("Service creation should fail for invalid initial state");
  }
 catch (  Exception e) {
  }
  createService(clusterName,serviceName,null);
  String serviceName2="MAPREDUCE";
  createService(clusterName,serviceName2,State.INIT);
  ServiceRequest r=new ServiceRequest(clusterName,null,null,null);
  Set<ServiceResponse> response=controller.getServices(Collections.singleton(r));
  Assert.assertEquals(2,response.size());
  for (  ServiceResponse svc : response) {
    Assert.assertTrue(svc.getServiceName().equals(serviceName) || svc.getServiceName().equals(serviceName2));
    Assert.assertEquals("HDP-0.1",svc.getDesiredStackVersion());
    Assert.assertEquals(State.INIT.toString(),svc.getDesiredState());
  }
}
