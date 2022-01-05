@Test public void testCreateService() throws AmbariException {
  String serviceName="HDFS";
  Service s=serviceFactory.createNew(cluster,serviceName);
  cluster.addService(s);
  s.persist();
  Service service=cluster.getService(serviceName);
  Assert.assertNotNull(service);
  Assert.assertEquals(serviceName,service.getName());
  Assert.assertEquals(cluster.getClusterId(),service.getCluster().getClusterId());
  Assert.assertEquals(cluster.getClusterName(),service.getCluster().getClusterName());
  Assert.assertEquals(State.INIT,service.getDesiredState());
  Assert.assertFalse(service.getDesiredStackVersion().getStackId().isEmpty());
}
