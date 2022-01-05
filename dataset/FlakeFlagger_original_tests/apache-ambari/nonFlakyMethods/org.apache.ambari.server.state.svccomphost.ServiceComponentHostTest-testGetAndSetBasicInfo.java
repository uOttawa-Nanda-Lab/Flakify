@Test public void testGetAndSetBasicInfo() throws AmbariException {
  ServiceComponentHost sch=createNewServiceComponentHost("HDFS","NAMENODE","h1",false);
  sch.setDesiredState(State.INSTALLED);
  sch.setState(State.INSTALLING);
  sch.setStackVersion(new StackId("HDP-1.0.0"));
  sch.setDesiredStackVersion(new StackId("HDP-1.1.0"));
  Assert.assertEquals(State.INSTALLING,sch.getState());
  Assert.assertEquals(State.INSTALLED,sch.getDesiredState());
  Assert.assertEquals("HDP-1.0.0",sch.getStackVersion().getStackId());
  Assert.assertEquals("HDP-1.1.0",sch.getDesiredStackVersion().getStackId());
}
