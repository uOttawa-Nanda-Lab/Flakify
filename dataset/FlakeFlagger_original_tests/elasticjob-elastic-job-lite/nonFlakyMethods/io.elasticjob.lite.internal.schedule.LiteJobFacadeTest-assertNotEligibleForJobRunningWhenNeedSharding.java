@Test public void assertNotEligibleForJobRunningWhenNeedSharding(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).build());
  when(shardingService.isNeedSharding()).thenReturn(true);
  assertThat(liteJobFacade.isEligibleForJobRunning(),is(false));
  verify(shardingService).isNeedSharding();
}
