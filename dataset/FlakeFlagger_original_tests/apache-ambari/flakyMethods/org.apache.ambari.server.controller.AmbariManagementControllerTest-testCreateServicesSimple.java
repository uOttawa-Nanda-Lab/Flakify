@Test public void testCreateServicesSimple() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  String serviceName="HDFS";
  clusters.getCluster("foo1").setDesiredStackVersion(new StackId("HDP-0.1"));
  createService(clusterName,serviceName,State.INIT);
  Service s=clusters.getCluster(clusterName).getService(serviceName);
  Assert.assertNotNull(s);
  Assert.assertEquals(serviceName,s.getName());
  Assert.assertEquals(clusterName,s.getCluster().getClusterName());
  ServiceRequest req=new ServiceRequest(clusterName,serviceName,null,null);
  Set<ServiceResponse> r=controller.getServices(Collections.singleton(req));
  Assert.assertEquals(1,r.size());
  ServiceResponse resp=r.iterator().next();
  Assert.assertEquals(serviceName,resp.getServiceName());
  Assert.assertEquals(clusterName,resp.getClusterName());
  Assert.assertEquals(State.INIT.toString(),resp.getDesiredState());
  Assert.assertEquals("HDP-0.1",resp.getDesiredStackVersion());
}
