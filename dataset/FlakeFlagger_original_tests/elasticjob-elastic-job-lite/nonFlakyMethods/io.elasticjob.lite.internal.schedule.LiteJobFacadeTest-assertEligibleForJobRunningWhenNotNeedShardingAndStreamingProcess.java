@Test public void assertEligibleForJobRunningWhenNotNeedShardingAndStreamingProcess(){
  when(shardingService.isNeedSharding()).thenReturn(false);
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).build());
  assertThat(liteJobFacade.isEligibleForJobRunning(),is(true));
  verify(shardingService).isNeedSharding();
  verify(configService).load(true);
}
