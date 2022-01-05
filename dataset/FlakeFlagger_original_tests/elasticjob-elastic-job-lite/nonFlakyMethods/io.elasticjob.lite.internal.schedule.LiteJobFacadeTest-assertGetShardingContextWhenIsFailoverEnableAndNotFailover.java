@Test public void assertGetShardingContextWhenIsFailoverEnableAndNotFailover(){
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  when(failoverService.getLocalFailoverItems()).thenReturn(Collections.<Integer>emptyList());
  when(shardingService.getLocalShardingItems()).thenReturn(Lists.newArrayList(0,1));
  when(failoverService.getLocalTakeOffItems()).thenReturn(Collections.singletonList(0));
  when(executionContextService.getJobShardingContext(Collections.singletonList(1))).thenReturn(shardingContexts);
  assertThat(liteJobFacade.getShardingContexts(),is(shardingContexts));
  verify(shardingService).shardingIfNecessary();
}
