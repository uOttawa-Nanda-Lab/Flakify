@Test(expected=JobSystemException.class) public void assertAfterJobExecutedFailure(){
  ShardingContexts shardingContexts=ShardingContextsBuilder.getMultipleShardingContexts();
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  when(jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())).thenReturn(false);
  when(jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())).thenReturn(false);
  doThrow(RuntimeException.class).when(jobFacade).afterJobExecuted(shardingContexts);
  try {
    simpleJobExecutor.execute();
  }
  finally {
    verify(jobCaller,times(2)).execute();
  }
}
