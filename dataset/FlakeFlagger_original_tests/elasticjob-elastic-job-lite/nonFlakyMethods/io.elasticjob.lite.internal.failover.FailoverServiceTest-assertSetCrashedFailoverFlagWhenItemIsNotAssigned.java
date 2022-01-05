@Test public void assertSetCrashedFailoverFlagWhenItemIsNotAssigned(){
  when(jobNodeStorage.isJobNodeExisted("sharding/0/failover")).thenReturn(true);
  failoverService.setCrashedFailoverFlag(0);
  verify(jobNodeStorage).isJobNodeExisted("sharding/0/failover");
  verify(jobNodeStorage,times(0)).createJobNodeIfNeeded("leader/failover/items/0");
}
