@Test public void assertFailoverIfUnnecessaryWhenServerIsNotReady(){
  JobRegistry.getInstance().setJobRunning("test_job",true);
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(true);
  when(jobNodeStorage.getJobNodeChildrenKeys("leader/failover/items")).thenReturn(Arrays.asList("0","1","2"));
  failoverService.failoverIfNecessary();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage).getJobNodeChildrenKeys("leader/failover/items");
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/failover/latch"),ArgumentMatchers.<FailoverService.FailoverLeaderExecutionCallback>any());
}
