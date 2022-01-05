@Test public void assertGetShardingContextWhenIsFailoverEnableAndFailover(){
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  when(failoverService.getLocalFailoverItems()).thenReturn(Collections.singletonList(1));
  when(executionContextService.getJobShardingContext(Collections.singletonList(1))).thenReturn(shardingContexts);
  assertThat(liteJobFacade.getShardingContexts(),is(shardingContexts));
  verify(shardingService,times(0)).shardingIfNecessary();
}
