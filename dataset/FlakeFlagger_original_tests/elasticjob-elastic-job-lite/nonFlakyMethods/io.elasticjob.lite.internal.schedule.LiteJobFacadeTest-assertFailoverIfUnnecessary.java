@Test public void assertFailoverIfUnnecessary(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(false).build(),TestSimpleJob.class.getCanonicalName())).build());
  liteJobFacade.failoverIfNecessary();
  verify(failoverService,times(0)).failoverIfNecessary();
}
