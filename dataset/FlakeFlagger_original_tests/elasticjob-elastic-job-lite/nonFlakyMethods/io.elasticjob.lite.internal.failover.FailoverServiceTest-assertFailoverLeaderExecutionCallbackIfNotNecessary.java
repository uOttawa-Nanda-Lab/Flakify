@Test public void assertFailoverLeaderExecutionCallbackIfNotNecessary(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  JobRegistry.getInstance().setJobRunning("test_job",false);
  when(jobNodeStorage.isJobNodeExisted("leader/failover/items")).thenReturn(false);
  failoverService.new FailoverLeaderExecutionCallback().execute();
  verify(jobNodeStorage).isJobNodeExisted("leader/failover/items");
  verify(jobNodeStorage,times(0)).getJobNodeChildrenKeys("leader/failover/items");
  JobRegistry.getInstance().shutdown("test_job");
}
