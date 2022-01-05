@Test public void testGetServicesWithFilters() throws AmbariException {
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  Cluster c1=clusters.getCluster("c1");
  Cluster c2=clusters.getCluster("c2");
  c1.setDesiredStackVersion(new StackId("HDP-0.2"));
  c2.setDesiredStackVersion(new StackId("HDP-0.2"));
  Service s1=serviceFactory.createNew(c1,"HDFS");
  Service s2=serviceFactory.createNew(c1,"MAPREDUCE");
  Service s3=serviceFactory.createNew(c1,"HBASE");
  Service s4=serviceFactory.createNew(c2,"HIVE");
  Service s5=serviceFactory.createNew(c2,"ZOOKEEPER");
  c1.addService(s1);
  c1.addService(s2);
  c1.addService(s3);
  c2.addService(s4);
  c2.addService(s5);
  s1.setDesiredState(State.INSTALLED);
  s2.setDesiredState(State.INSTALLED);
  s4.setDesiredState(State.INSTALLED);
  s1.persist();
  s2.persist();
  s3.persist();
  s4.persist();
  s5.persist();
  ServiceRequest r=new ServiceRequest(null,null,null,null);
  Set<ServiceResponse> resp;
  try {
    controller.getServices(Collections.singleton(r));
    fail("Expected failure for invalid request");
  }
 catch (  Exception e) {
  }
  r=new ServiceRequest(c1.getClusterName(),null,null,null);
  resp=controller.getServices(Collections.singleton(r));
  Assert.assertEquals(3,resp.size());
  r=new ServiceRequest(c1.getClusterName(),s2.getName(),null,null);
  resp=controller.getServices(Collections.singleton(r));
  Assert.assertEquals(1,resp.size());
  Assert.assertEquals(s2.getName(),resp.iterator().next().getServiceName());
  try {
    r=new ServiceRequest(c2.getClusterName(),s1.getName(),null,null);
    resp=controller.getServices(Collections.singleton(r));
    fail("Expected failure for invalid service");
  }
 catch (  Exception e) {
  }
  r=new ServiceRequest(c1.getClusterName(),null,null,"INSTALLED");
  resp=controller.getServices(Collections.singleton(r));
  Assert.assertEquals(2,resp.size());
  r=new ServiceRequest(c2.getClusterName(),null,null,"INIT");
  resp=controller.getServices(Collections.singleton(r));
  Assert.assertEquals(1,resp.size());
  ServiceRequest r1, r2, r3;
  r1=new ServiceRequest(c1.getClusterName(),null,null,"INSTALLED");
  r2=new ServiceRequest(c2.getClusterName(),null,null,"INIT");
  r3=new ServiceRequest(c2.getClusterName(),null,null,"INIT");
  Set<ServiceRequest> reqs=new HashSet<ServiceRequest>();
  reqs.addAll(Arrays.asList(r1,r2,r3));
  resp=controller.getServices(reqs);
  Assert.assertEquals(3,resp.size());
}
