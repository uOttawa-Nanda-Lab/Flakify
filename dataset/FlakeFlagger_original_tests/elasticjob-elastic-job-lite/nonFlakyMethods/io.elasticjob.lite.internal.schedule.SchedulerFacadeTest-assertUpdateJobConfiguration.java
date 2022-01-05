@Test public void assertUpdateJobConfiguration(){
  LiteJobConfiguration jobConfig=LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),false)).build();
  when(configService.load(false)).thenReturn(jobConfig);
  assertThat(schedulerFacade.updateJobConfiguration(jobConfig),is(jobConfig));
  verify(configService).persist(jobConfig);
}
