@Test public void assertFailoverIfUnnecessaryWhenItemsRootNodeIsEmpty(){
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(true);
  when(jobNodeStorage.getJobNodeChildrenKeys("leader/failover/items")).thenReturn(Collections.<String>emptyList());
  failoverService.failoverIfNecessary();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage).getJobNodeChildrenKeys("leader/failover/items");
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/failover/latch"),ArgumentMatchers.<FailoverService.FailoverLeaderExecutionCallback>any());
}
