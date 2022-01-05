@Test public void assertNotHaveRunningItemsWithoutMonitorExecution(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build());
  assertFalse(executionService.hasRunningItems(Arrays.asList(0,1,2)));
}
