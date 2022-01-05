@Test public void assertFailoverIfUnnecessaryWhenItemsRootNodeNotExisted(){
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(false);
  failoverService.failoverIfNecessary();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/failover/latch"),ArgumentMatchers.<FailoverService.FailoverLeaderExecutionCallback>any());
}
