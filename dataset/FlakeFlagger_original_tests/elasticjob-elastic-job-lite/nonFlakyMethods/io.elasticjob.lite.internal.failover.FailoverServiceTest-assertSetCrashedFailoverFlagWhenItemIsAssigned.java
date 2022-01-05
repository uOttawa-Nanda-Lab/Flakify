@Test public void assertSetCrashedFailoverFlagWhenItemIsAssigned(){
  when(jobNodeStorage.isJobNodeExisted("sharding/0/failover")).thenReturn(false);
  failoverService.setCrashedFailoverFlag(0);
  verify(jobNodeStorage).isJobNodeExisted("sharding/0/failover");
  verify(jobNodeStorage).createJobNodeIfNeeded("leader/failover/items/0");
}
