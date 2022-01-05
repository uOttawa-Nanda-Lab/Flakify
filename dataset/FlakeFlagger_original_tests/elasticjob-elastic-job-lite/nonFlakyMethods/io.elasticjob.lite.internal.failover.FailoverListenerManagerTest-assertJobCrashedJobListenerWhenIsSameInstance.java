@Test public void assertJobCrashedJobListenerWhenIsSameInstance(){
  JobRegistry.getInstance().addJobInstance("test_job",new JobInstance("127.0.0.1@-@0"));
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).build());
  failoverListenerManager.new JobCrashedJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(failoverService,times(0)).failoverIfNecessary();
  JobRegistry.getInstance().shutdown("test_job");
}
