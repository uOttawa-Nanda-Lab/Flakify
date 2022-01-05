@Test public void assertRegisterJobCompletedWithMonitorExecution(){
  JobRegistry.getInstance().setJobRunning("test_job",true);
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  executionService.registerJobCompleted(getShardingContext());
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/running");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/running");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/2/running");
  assertFalse(JobRegistry.getInstance().isJobRunning("test_job"));
}
