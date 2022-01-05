@Test public void assertJobCrashedJobListenerWhenIsOtherFailoverInstanceCrashed(){
  JobRegistry.getInstance().addJobInstance("test_job",new JobInstance("127.0.0.1@-@0"));
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(failoverService.getFailoverItems("127.0.0.1@-@1")).thenReturn(Collections.singletonList(1));
  when(shardingService.getShardingItems("127.0.0.1@-@1")).thenReturn(Arrays.asList(0,2));
  failoverListenerManager.new JobCrashedJobListener().dataChanged("/test_job/instances/127.0.0.1@-@1",Type.NODE_REMOVED,"");
  verify(failoverService).setCrashedFailoverFlag(1);
  verify(failoverService).failoverIfNecessary();
  JobRegistry.getInstance().shutdown("test_job");
}
