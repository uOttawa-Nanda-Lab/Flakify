@Test public void assertMisfireIfRunning(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  when(jobNodeStorage.isJobNodeExisted("sharding/0/running")).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/running")).thenReturn(true);
  assertTrue(executionService.misfireIfHasRunningItems(Arrays.asList(0,1,2)));
}
