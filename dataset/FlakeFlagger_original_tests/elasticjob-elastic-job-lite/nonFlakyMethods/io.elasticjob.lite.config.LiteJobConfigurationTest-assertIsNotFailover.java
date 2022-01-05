@Test public void assertIsNotFailover(){
  assertFalse(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(false).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build().isFailover());
}
