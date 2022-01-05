@Test public void assertRegisterJobCompletedWithoutMonitorExecution(){
  JobRegistry.getInstance().setJobRunning("test_job",true);
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build());
  executionService.registerJobCompleted(new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap()));
  verify(jobNodeStorage,times(0)).removeJobNodeIfExisted((String)any());
  verify(jobNodeStorage,times(0)).createJobNodeIfNeeded((String)any());
  assertFalse(JobRegistry.getInstance().isJobRunning("test_job"));
}
