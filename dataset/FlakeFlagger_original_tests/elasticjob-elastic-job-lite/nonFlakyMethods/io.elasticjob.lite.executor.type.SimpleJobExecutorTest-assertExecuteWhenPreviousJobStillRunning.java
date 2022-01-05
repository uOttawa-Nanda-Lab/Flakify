@Test public void assertExecuteWhenPreviousJobStillRunning() throws JobExecutionEnvironmentException {
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  when(jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())).thenReturn(true);
  simpleJobExecutor.execute();
  verify(jobFacade).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_STAGING,"Job 'test_job' execute begin.");
  verify(jobFacade).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_FINISHED,"Previous job 'test_job' - shardingItems '[]' is still running, misfired job will start after previous job completed.");
  verify(jobFacade).checkJobExecutionEnvironment();
  verify(jobFacade).getShardingContexts();
  verify(jobFacade).misfireIfRunning(shardingContexts.getShardingItemParameters().keySet());
  verify(jobCaller,times(0)).execute();
}
