@Test public void assertMisfireIfNotRunning(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  when(jobNodeStorage.isJobNodeExisted("sharding/0/running")).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/running")).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("sharding/2/running")).thenReturn(false);
  assertFalse(executionService.misfireIfHasRunningItems(Arrays.asList(0,1,2)));
}
