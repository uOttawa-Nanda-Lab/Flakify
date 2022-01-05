@Test public void assertBuildAllProperties(){
  JobCoreConfiguration actual=JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).shardingItemParameters("0=a,1=b,2=c").jobParameter("param").failover(true).misfire(false).description("desc").jobProperties("job_exception_handler",IgnoreJobExceptionHandler.class.getName()).build();
  assertRequiredProperties(actual);
  assertThat(actual.getShardingItemParameters(),is("0=a,1=b,2=c"));
  assertThat(actual.getJobParameter(),is("param"));
  assertTrue(actual.isFailover());
  assertFalse(actual.isMisfire());
  assertThat(actual.getDescription(),is("desc"));
  assertThat(actual.getJobProperties().get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER),is(IgnoreJobExceptionHandler.class.getName()));
}
