@Test public void assertGetShardingContextWhenNotAssignShardingItem(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).monitorExecution(false).build());
  ShardingContexts shardingContexts=executionContextService.getJobShardingContext(Collections.<Integer>emptyList());
  assertTrue(shardingContexts.getTaskId().startsWith("test_job@-@@-@READY@-@"));
  assertThat(shardingContexts.getShardingTotalCount(),is(3));
}
