@Test public void testGetServices() throws AmbariException {
  clusters.addCluster("c1");
  Cluster c1=clusters.getCluster("c1");
  c1.setDesiredStackVersion(new StackId("HDP-0.1"));
  Service s1=serviceFactory.createNew(c1,"HDFS");
  c1.addService(s1);
  s1.setDesiredStackVersion(new StackId("HDP-0.1"));
  s1.setDesiredState(State.INSTALLED);
  s1.persist();
  ServiceRequest r=new ServiceRequest("c1",null,null,null);
  Set<ServiceResponse> resp=controller.getServices(Collections.singleton(r));
  ServiceResponse resp1=resp.iterator().next();
  Assert.assertEquals(s1.getClusterId(),resp1.getClusterId().longValue());
  Assert.assertEquals(s1.getCluster().getClusterName(),resp1.getClusterName());
  Assert.assertEquals(s1.getName(),resp1.getServiceName());
  Assert.assertEquals("HDP-0.1",s1.getDesiredStackVersion().getStackId());
  Assert.assertEquals(s1.getDesiredStackVersion().getStackId(),resp1.getDesiredStackVersion());
  Assert.assertEquals(State.INSTALLED.toString(),resp1.getDesiredState());
}
