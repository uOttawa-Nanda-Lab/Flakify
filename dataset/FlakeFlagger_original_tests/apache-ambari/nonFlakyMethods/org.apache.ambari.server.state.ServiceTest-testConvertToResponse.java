@Test public void testConvertToResponse() throws AmbariException {
  String serviceName="HDFS";
  Service s=serviceFactory.createNew(cluster,serviceName);
  cluster.addService(s);
  Service service=cluster.getService(serviceName);
  Assert.assertNotNull(service);
  ServiceResponse r=s.convertToResponse();
  Assert.assertEquals(s.getName(),r.getServiceName());
  Assert.assertEquals(s.getCluster().getClusterName(),r.getClusterName());
  Assert.assertEquals(s.getDesiredStackVersion().getStackId(),r.getDesiredStackVersion());
  Assert.assertEquals(s.getDesiredState().toString(),r.getDesiredState());
  service.setDesiredStackVersion(new StackId("HDP-1.1.0"));
  service.setDesiredState(State.INSTALLING);
  r=s.convertToResponse();
  Assert.assertEquals(s.getName(),r.getServiceName());
  Assert.assertEquals(s.getCluster().getClusterName(),r.getClusterName());
  Assert.assertEquals(s.getDesiredStackVersion().getStackId(),r.getDesiredStackVersion());
  Assert.assertEquals(s.getDesiredState().toString(),r.getDesiredState());
  StringBuilder sb=new StringBuilder();
  s.debugDump(sb);
  Assert.assertFalse(sb.toString().isEmpty());
}
