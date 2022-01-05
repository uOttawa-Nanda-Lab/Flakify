@Test public void testConvertToResponse() throws AmbariException {
  ServiceComponentHost sch=createNewServiceComponentHost("HDFS","DATANODE","h1",false);
  sch.setDesiredState(State.INSTALLED);
  sch.setState(State.INSTALLING);
  sch.setStackVersion(new StackId("HDP-1.0.0"));
  ServiceComponentHostResponse r=sch.convertToResponse();
  Assert.assertEquals("HDFS",r.getServiceName());
  Assert.assertEquals("DATANODE",r.getComponentName());
  Assert.assertEquals("h1",r.getHostname());
  Assert.assertEquals("C1",r.getClusterName());
  Assert.assertEquals(State.INSTALLED.toString(),r.getDesiredState());
  Assert.assertEquals(State.INSTALLING.toString(),r.getLiveState());
  Assert.assertEquals("HDP-1.0.0",r.getStackVersion());
  StringBuilder sb=new StringBuilder();
  sch.debugDump(sb);
  Assert.assertFalse(sb.toString().isEmpty());
}
