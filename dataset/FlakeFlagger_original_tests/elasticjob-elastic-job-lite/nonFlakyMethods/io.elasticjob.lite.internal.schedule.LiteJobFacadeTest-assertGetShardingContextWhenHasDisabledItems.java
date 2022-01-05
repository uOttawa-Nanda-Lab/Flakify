@Test public void assertGetShardingContextWhenHasDisabledItems(){
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(false).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(shardingService.getLocalShardingItems()).thenReturn(Lists.newArrayList(0,1));
  when(executionService.getDisabledItems(Lists.newArrayList(0,1))).thenReturn(Collections.singletonList(1));
  when(executionContextService.getJobShardingContext(Lists.newArrayList(0))).thenReturn(shardingContexts);
  assertThat(liteJobFacade.getShardingContexts(),is(shardingContexts));
  verify(shardingService).shardingIfNecessary();
}
