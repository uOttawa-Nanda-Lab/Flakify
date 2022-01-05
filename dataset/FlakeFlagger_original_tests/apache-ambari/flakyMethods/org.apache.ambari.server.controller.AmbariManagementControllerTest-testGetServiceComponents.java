@Test public void testGetServiceComponents() throws AmbariException {
  clusters.addCluster("c1");
  Cluster c1=clusters.getCluster("c1");
  c1.setDesiredStackVersion(new StackId("HDP-0.2"));
  Service s1=serviceFactory.createNew(c1,"HDFS");
  c1.addService(s1);
  s1.setDesiredState(State.INSTALLED);
  s1.persist();
  ServiceComponent sc1=serviceComponentFactory.createNew(s1,"DATANODE");
  s1.addServiceComponent(sc1);
  sc1.persist();
  sc1.setDesiredStackVersion(new StackId("HDP-0.1"));
  sc1.setDesiredState(State.UNINSTALLED);
  ServiceComponentRequest r=new ServiceComponentRequest("c1",s1.getName(),sc1.getName(),null,null);
  Set<ServiceComponentResponse> resps=controller.getComponents(Collections.singleton(r));
  Assert.assertEquals(1,resps.size());
  ServiceComponentResponse resp=resps.iterator().next();
  Assert.assertEquals(c1.getClusterName(),resp.getClusterName());
  Assert.assertEquals(sc1.getName(),resp.getComponentName());
  Assert.assertEquals(s1.getName(),resp.getServiceName());
  Assert.assertEquals("HDP-0.1",resp.getDesiredStackVersion());
  Assert.assertEquals(sc1.getDesiredState().toString(),resp.getDesiredState());
  Assert.assertEquals(c1.getClusterId(),resp.getClusterId().longValue());
}
