@Test public void assertBuildWhenOptionalParametersIsNull(){
  JobCoreConfiguration actual=JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).shardingItemParameters(null).jobParameter(null).description(null).build();
  assertRequiredProperties(actual);
  assertDefaultValues(actual);
}
