@Test public void assertJobCrashedJobListenerWhenIsOtherInstanceCrashed(){
  JobRegistry.getInstance().addJobInstance("test_job",new JobInstance("127.0.0.1@-@0"));
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(shardingService.getShardingItems("127.0.0.1@-@1")).thenReturn(Arrays.asList(0,2));
  failoverListenerManager.new JobCrashedJobListener().dataChanged("/test_job/instances/127.0.0.1@-@1",Type.NODE_REMOVED,"");
  verify(failoverService).setCrashedFailoverFlag(0);
  verify(failoverService).setCrashedFailoverFlag(2);
  verify(failoverService,times(2)).failoverIfNecessary();
  JobRegistry.getInstance().shutdown("test_job");
}
