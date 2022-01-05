@Test public void assertExecuteWhenRunOnceWithMisfire() throws JobExecutionEnvironmentException {
  ShardingContexts shardingContexts=ShardingContextsBuilder.getMultipleShardingContexts();
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  when(jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())).thenReturn(false);
  when(jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())).thenReturn(true,false);
  simpleJobExecutor.execute();
  verify(jobFacade).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_STAGING,"Job 'test_job' execute begin.");
  verify(jobFacade,times(2)).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_RUNNING,"");
  verify(jobFacade).checkJobExecutionEnvironment();
  verify(jobFacade).getShardingContexts();
  verify(jobFacade).misfireIfRunning(shardingContexts.getShardingItemParameters().keySet());
  verify(jobFacade,times(2)).registerJobBegin(shardingContexts);
  verify(jobCaller,times(4)).execute();
  verify(jobFacade,times(2)).registerJobCompleted(shardingContexts);
}
