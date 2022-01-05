@Test public void assertFailoverIfNecessary(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  liteJobFacade.failoverIfNecessary();
  verify(failoverService).failoverIfNecessary();
}
