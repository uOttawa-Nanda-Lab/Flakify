@Test public void assertRegisterJobCompletedWhenFailoverEnabled(){
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).failover(true).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(true).build());
  liteJobFacade.registerJobCompleted(shardingContexts);
  verify(executionService).registerJobCompleted(shardingContexts);
  verify(failoverService).updateFailoverComplete(shardingContexts.getShardingItemParameters().keySet());
}
