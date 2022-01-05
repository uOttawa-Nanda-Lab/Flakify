@Test public void testGetAndSetServiceInfo() throws AmbariException {
  String serviceName="HDFS";
  Service s=serviceFactory.createNew(cluster,serviceName);
  cluster.addService(s);
  s.persist();
  Service service=cluster.getService(serviceName);
  Assert.assertNotNull(service);
  service.setDesiredStackVersion(new StackId("HDP-1.1.0"));
  Assert.assertEquals("HDP-1.1.0",service.getDesiredStackVersion().getStackId());
  service.setDesiredState(State.INSTALLING);
  Assert.assertEquals(State.INSTALLING,service.getDesiredState());
}
