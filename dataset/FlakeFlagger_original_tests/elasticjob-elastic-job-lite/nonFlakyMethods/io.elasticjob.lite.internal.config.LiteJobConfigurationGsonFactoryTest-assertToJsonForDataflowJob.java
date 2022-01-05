@Test public void assertToJsonForDataflowJob(){
  LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).build();
  assertThat(LiteJobConfigurationGsonFactory.toJson(actual),is(dataflowJobJson));
}
