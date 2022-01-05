@Test public void assertUpdateFailoverComplete(){
  failoverService.updateFailoverComplete(Arrays.asList(0,1));
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/failover");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/failover");
}
