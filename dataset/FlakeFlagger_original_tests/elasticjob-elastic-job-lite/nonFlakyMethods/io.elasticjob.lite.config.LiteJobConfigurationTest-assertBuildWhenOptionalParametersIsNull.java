@Test public void assertBuildWhenOptionalParametersIsNull(){
  assertThat(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).jobShardingStrategyClass(null).build().getJobShardingStrategyClass(),is(""));
}
