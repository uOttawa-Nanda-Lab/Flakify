@Test public void testServiceUpdateBasic() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  String serviceName="HDFS";
  clusters.getCluster("foo1").setDesiredStackVersion(new StackId("HDP-0.2"));
  createService(clusterName,serviceName,State.INIT);
  Service s=clusters.getCluster(clusterName).getService(serviceName);
  Assert.assertNotNull(s);
  Assert.assertEquals(serviceName,s.getName());
  Assert.assertEquals(State.INIT,s.getDesiredState());
  Assert.assertEquals(clusterName,s.getCluster().getClusterName());
  Set<ServiceRequest> reqs=new HashSet<ServiceRequest>();
  ServiceRequest r;
  try {
    r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLING.toString());
    reqs.clear();
    reqs.add(r);
    controller.updateServices(reqs);
    fail("Expected fail for invalid state transition");
  }
 catch (  Exception e) {
  }
  r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLED.toString());
  reqs.clear();
  reqs.add(r);
  RequestStatusResponse trackAction=controller.updateServices(reqs);
  Assert.assertNull(trackAction);
}
