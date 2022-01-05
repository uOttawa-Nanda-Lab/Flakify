@Test(expected=JobSystemException.class) public void assertBeforeJobExecutedFailure(){
  ShardingContexts shardingContexts=ShardingContextsBuilder.getMultipleShardingContexts();
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  when(jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())).thenReturn(false);
  doThrow(RuntimeException.class).when(jobFacade).beforeJobExecuted(shardingContexts);
  try {
    simpleJobExecutor.execute();
  }
  finally {
    verify(jobCaller,times(0)).execute();
  }
}
