@Test public void assertFailoverIfNecessary(){
  JobRegistry.getInstance().setJobRunning("test_job",false);
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(true);
  when(jobNodeStorage.getJobNodeChildrenKeys("leader/failover/items")).thenReturn(Arrays.asList("0","1","2"));
  failoverService.failoverIfNecessary();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage).getJobNodeChildrenKeys("leader/failover/items");
  verify(jobNodeStorage).executeInLeader(eq("leader/failover/latch"),ArgumentMatchers.<FailoverService.FailoverLeaderExecutionCallback>any());
  JobRegistry.getInstance().setJobRunning("test_job",false);
}
