@Test public void assertFromJsonForDataflowJob(){
  LiteJobConfiguration actual=LiteJobConfigurationGsonFactory.fromJson(dataflowJobJson);
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getTypeConfig().getJobClass(),is(TestDataflowJob.class.getCanonicalName()));
  assertThat(actual.getTypeConfig().getJobType(),is(JobType.DATAFLOW));
  assertThat(actual.getTypeConfig().getCoreConfig().getCron(),is("0/1 * * * * ?"));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingTotalCount(),is(3));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingItemParameters(),is(""));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobParameter(),is(""));
  assertFalse(actual.getTypeConfig().getCoreConfig().isFailover());
  assertTrue(actual.getTypeConfig().getCoreConfig().isMisfire());
  assertThat(actual.getTypeConfig().getCoreConfig().getDescription(),is(""));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER),is(DefaultJobExceptionHandler.class.getCanonicalName()));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER),is(DefaultExecutorServiceHandler.class.getCanonicalName()));
  assertTrue(actual.isMonitorExecution());
  assertThat(actual.getMaxTimeDiffSeconds(),is(-1));
  assertThat(actual.getMonitorPort(),is(-1));
  assertThat(actual.getJobShardingStrategyClass(),is(""));
  assertThat(actual.getReconcileIntervalMinutes(),is(10));
  assertFalse(actual.isDisabled());
  assertFalse(actual.isOverwrite());
  assertTrue(((DataflowJobConfiguration)actual.getTypeConfig()).isStreamingProcess());
}
