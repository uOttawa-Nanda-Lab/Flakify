@Test public void assertFromJsonForSimpleJob(){
  LiteJobConfiguration actual=LiteJobConfigurationGsonFactory.fromJson(simpleJobJson);
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getTypeConfig().getJobClass(),is(TestSimpleJob.class.getCanonicalName()));
  assertThat(actual.getTypeConfig().getJobType(),is(JobType.SIMPLE));
  assertThat(actual.getTypeConfig().getCoreConfig().getCron(),is("0/1 * * * * ?"));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingTotalCount(),is(3));
  assertThat(actual.getTypeConfig().getCoreConfig().getShardingItemParameters(),is(""));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobParameter(),is(""));
  assertTrue(actual.getTypeConfig().getCoreConfig().isFailover());
  assertFalse(actual.getTypeConfig().getCoreConfig().isMisfire());
  assertThat(actual.getTypeConfig().getCoreConfig().getDescription(),is(""));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER),is(DefaultJobExceptionHandler.class.getCanonicalName()));
  assertThat(actual.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER),is(DefaultExecutorServiceHandler.class.getCanonicalName()));
  assertFalse(actual.isMonitorExecution());
  assertThat(actual.getMaxTimeDiffSeconds(),is(1000));
  assertThat(actual.getMonitorPort(),is(8888));
  assertThat(actual.getJobShardingStrategyClass(),is("testClass"));
  assertThat(actual.getReconcileIntervalMinutes(),is(15));
  assertTrue(actual.isDisabled());
  assertTrue(actual.isOverwrite());
}
