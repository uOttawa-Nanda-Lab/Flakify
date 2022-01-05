@Test public void assertClearAllRunningInfo(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build());
  executionService.clearAllRunningInfo();
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/running");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/running");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/2/running");
}
