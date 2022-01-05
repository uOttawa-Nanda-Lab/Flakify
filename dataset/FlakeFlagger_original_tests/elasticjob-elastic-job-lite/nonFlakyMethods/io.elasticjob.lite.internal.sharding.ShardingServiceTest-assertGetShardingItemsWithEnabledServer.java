@Test public void assertGetShardingItemsWithEnabledServer(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(serverService.isAvailableServer("127.0.0.1")).thenReturn(true);
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.getJobNodeData("sharding/0/instance")).thenReturn("127.0.0.1@-@0");
  when(jobNodeStorage.getJobNodeData("sharding/1/instance")).thenReturn("127.0.0.1@-@1");
  when(jobNodeStorage.getJobNodeData("sharding/2/instance")).thenReturn("127.0.0.1@-@0");
  assertThat(shardingService.getShardingItems("127.0.0.1@-@0"),is(Arrays.asList(0,2)));
  JobRegistry.getInstance().shutdown("test_job");
}
