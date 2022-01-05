@Test public void testCreateServiceComponentSimple() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  String serviceName="HDFS";
  createService(clusterName,serviceName,null);
  String componentName="NAMENODE";
  try {
    createServiceComponent(clusterName,serviceName,componentName,State.INSTALLING);
    fail("ServiceComponent creation should fail for invalid state");
  }
 catch (  Exception e) {
  }
  try {
    clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName);
    fail("ServiceComponent creation should have failed");
  }
 catch (  Exception e) {
  }
  createServiceComponent(clusterName,serviceName,componentName,State.INIT);
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName));
  ServiceComponentRequest r=new ServiceComponentRequest(clusterName,serviceName,null,null,null);
  Set<ServiceComponentResponse> response=controller.getComponents(Collections.singleton(r));
  Assert.assertEquals(1,response.size());
  ServiceComponentResponse sc=response.iterator().next();
  Assert.assertEquals(State.INIT.toString(),sc.getDesiredState());
  Assert.assertEquals(componentName,sc.getComponentName());
  Assert.assertEquals(clusterName,sc.getClusterName());
  Assert.assertEquals(serviceName,sc.getServiceName());
}
