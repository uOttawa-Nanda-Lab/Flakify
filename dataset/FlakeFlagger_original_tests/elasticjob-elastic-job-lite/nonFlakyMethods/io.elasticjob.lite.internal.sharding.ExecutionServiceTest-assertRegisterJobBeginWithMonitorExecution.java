@Test public void assertRegisterJobBeginWithMonitorExecution(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  executionService.registerJobBegin(getShardingContext());
  verify(jobNodeStorage).fillEphemeralJobNode("sharding/0/running","");
  verify(jobNodeStorage).fillEphemeralJobNode("sharding/1/running","");
  verify(jobNodeStorage).fillEphemeralJobNode("sharding/2/running","");
  assertTrue(JobRegistry.getInstance().isJobRunning("test_job"));
}
