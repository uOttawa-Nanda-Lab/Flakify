@Test public void assertNotEligibleForJobRunningWhenUnStreamingProcess(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),false)).build());
  assertThat(liteJobFacade.isEligibleForJobRunning(),is(false));
  verify(configService).load(true);
}
