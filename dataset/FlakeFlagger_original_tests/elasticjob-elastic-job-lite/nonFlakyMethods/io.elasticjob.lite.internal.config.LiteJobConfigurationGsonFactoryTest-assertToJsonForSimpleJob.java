@Test public void assertToJsonForSimpleJob(){
  LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).misfire(false).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).maxTimeDiffSeconds(1000).monitorPort(8888).jobShardingStrategyClass("testClass").disabled(true).overwrite(true).reconcileIntervalMinutes(15).build();
  assertThat(LiteJobConfigurationGsonFactory.toJson(actual),is(simpleJobJson));
}
