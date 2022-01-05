@Test public void testAddAndGetServiceComponents() throws AmbariException {
  String serviceName="HDFS";
  Service s=serviceFactory.createNew(cluster,serviceName);
  cluster.addService(s);
  s.persist();
  Service service=cluster.getService(serviceName);
  Assert.assertNotNull(service);
  Assert.assertTrue(s.getServiceComponents().isEmpty());
  ServiceComponent sc1=serviceComponentFactory.createNew(s,"NAMENODE");
  ServiceComponent sc2=serviceComponentFactory.createNew(s,"DATANODE1");
  ServiceComponent sc3=serviceComponentFactory.createNew(s,"DATANODE2");
  Map<String,ServiceComponent> comps=new HashMap<String,ServiceComponent>();
  comps.put(sc1.getName(),sc1);
  comps.put(sc2.getName(),sc2);
  s.addServiceComponents(comps);
  Assert.assertEquals(2,s.getServiceComponents().size());
  Assert.assertNotNull(s.getServiceComponent(sc1.getName()));
  Assert.assertNotNull(s.getServiceComponent(sc2.getName()));
  try {
    s.getServiceComponent(sc3.getName());
    fail("Expected error when looking for invalid component");
  }
 catch (  Exception e) {
  }
  s.addServiceComponent(sc3);
  sc1.persist();
  sc2.persist();
  sc3.persist();
  ServiceComponent sc4=s.addServiceComponent("HDFS_CLIENT");
  Assert.assertNotNull(s.getServiceComponent(sc4.getName()));
  Assert.assertEquals(State.INIT,s.getServiceComponent("HDFS_CLIENT").getDesiredState());
  Assert.assertTrue(sc4.isClientComponent());
  sc4.persist();
  Assert.assertEquals(4,s.getServiceComponents().size());
  Assert.assertNotNull(s.getServiceComponent(sc3.getName()));
  Assert.assertEquals(sc3.getName(),s.getServiceComponent(sc3.getName()).getName());
  Assert.assertEquals(s.getName(),s.getServiceComponent(sc3.getName()).getServiceName());
  Assert.assertEquals(cluster.getClusterName(),s.getServiceComponent(sc3.getName()).getClusterName());
  sc4.setDesiredState(State.INSTALLING);
  Assert.assertEquals(State.INSTALLING,s.getServiceComponent("HDFS_CLIENT").getDesiredState());
}
