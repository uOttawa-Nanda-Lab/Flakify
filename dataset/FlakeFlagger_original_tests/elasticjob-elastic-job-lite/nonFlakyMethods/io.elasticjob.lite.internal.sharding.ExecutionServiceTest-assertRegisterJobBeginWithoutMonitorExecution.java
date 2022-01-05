@Test public void assertRegisterJobBeginWithoutMonitorExecution(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build());
  executionService.registerJobBegin(getShardingContext());
  verify(jobNodeStorage,times(0)).fillEphemeralJobNode((String)any(),any());
  assertTrue(JobRegistry.getInstance().isJobRunning("test_job"));
}
