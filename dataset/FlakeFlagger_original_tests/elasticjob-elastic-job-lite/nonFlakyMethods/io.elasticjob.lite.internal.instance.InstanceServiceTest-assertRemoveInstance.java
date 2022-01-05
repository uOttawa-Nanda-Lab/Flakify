@Test public void assertRemoveInstance(){
  instanceService.removeInstance();
  verify(jobNodeStorage).removeJobNodeIfExisted("instances/127.0.0.1@-@0");
}
