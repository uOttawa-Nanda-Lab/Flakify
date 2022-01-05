@Test public void assertExecuteWhenRunOnceWithMisfireIsNotEmptyButIsNotEligibleForJobRunning(){
  ShardingContexts shardingContexts=ShardingContextsBuilder.getMultipleShardingContexts();
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  when(jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())).thenReturn(false);
  simpleJobExecutor.execute();
  ElasticJobVerify.verifyForIsNotMisfire(jobFacade,shardingContexts);
  verify(jobCaller,times(2)).execute();
  verify(jobFacade,times(0)).clearMisfire(shardingContexts.getShardingItemParameters().keySet());
}
