@Test public void assertBuildRequiredProperties(){
  LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build();
  assertTrue(actual.isMonitorExecution());
  assertThat(actual.getMaxTimeDiffSeconds(),is(-1));
  assertThat(actual.getMonitorPort(),is(-1));
  assertThat(actual.getJobShardingStrategyClass(),is(""));
  assertFalse(actual.isDisabled());
  assertFalse(actual.isOverwrite());
}
