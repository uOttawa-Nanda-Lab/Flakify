@Test public void assertJobCrashedJobListenerWhenIsNotInstancesPath(){
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).build());
  failoverListenerManager.new JobCrashedJobListener().dataChanged("/test_job/other/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(failoverService,times(0)).failoverIfNecessary();
}
