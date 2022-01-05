@Test public void assertBuildAllProperties(){
  LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).maxTimeDiffSeconds(1000).monitorPort(8888).jobShardingStrategyClass("testClass").disabled(true).overwrite(true).reconcileIntervalMinutes(60).build();
  assertFalse(actual.isMonitorExecution());
  assertThat(actual.getMaxTimeDiffSeconds(),is(1000));
  assertThat(actual.getMonitorPort(),is(8888));
  assertThat(actual.getJobShardingStrategyClass(),is("testClass"));
  assertTrue(actual.isDisabled());
  assertTrue(actual.isOverwrite());
  assertThat(actual.getReconcileIntervalMinutes(),is(60));
}
