@Test public void assertExecuteWhenShardingItemsIsEmpty() throws JobExecutionEnvironmentException {
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  ElasticJobVerify.prepareForIsNotMisfire(jobFacade,shardingContexts);
  simpleJobExecutor.execute();
  verify(jobFacade).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_STAGING,"Job 'test_job' execute begin.");
  verify(jobFacade).postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_FINISHED,"Sharding item for job 'test_job' is empty.");
  verify(jobFacade).checkJobExecutionEnvironment();
  verify(jobFacade).getShardingContexts();
  verify(jobFacade).misfireIfRunning(shardingContexts.getShardingItemParameters().keySet());
  verify(jobCaller,times(0)).execute();
}
