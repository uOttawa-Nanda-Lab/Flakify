@Test public void assertIsFailover(){
  assertTrue(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build().isFailover());
}
