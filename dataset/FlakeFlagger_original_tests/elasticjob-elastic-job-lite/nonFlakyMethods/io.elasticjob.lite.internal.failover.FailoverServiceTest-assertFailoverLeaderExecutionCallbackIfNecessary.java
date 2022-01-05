@Test public void assertFailoverLeaderExecutionCallbackIfNecessary(){
  JobRegistry.getInstance().setJobRunning("test_job",false);
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(true);
  when(jobNodeStorage.getJobNodeChildrenKeys("leader/failover/items")).thenReturn(Arrays.asList("0","1","2"));
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  failoverService.new FailoverLeaderExecutionCallback().execute();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage,times(2)).getJobNodeChildrenKeys("leader/failover/items");
  verify(jobNodeStorage).fillEphemeralJobNode("sharding/0/failover","127.0.0.1@-@0");
  verify(jobNodeStorage).removeJobNodeIfExisted("leader/failover/items/0");
  verify(jobScheduleController).triggerJob();
  JobRegistry.getInstance().setJobRunning("test_job",false);
  JobRegistry.getInstance().shutdown("test_job");
}
