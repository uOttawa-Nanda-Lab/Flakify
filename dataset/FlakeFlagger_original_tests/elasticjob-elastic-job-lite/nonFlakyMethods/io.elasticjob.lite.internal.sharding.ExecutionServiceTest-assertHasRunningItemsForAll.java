@Test public void assertHasRunningItemsForAll(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.getJobNodeChildrenKeys("sharding")).thenReturn(Arrays.asList("0","1","2"));
  when(jobNodeStorage.isJobNodeExisted("sharding/0/running")).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/running")).thenReturn(true);
  assertTrue(executionService.hasRunningItems());
}
