@Test public void assertRegisterJobBegin(){
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap());
  liteJobFacade.registerJobBegin(shardingContexts);
  verify(executionService).registerJobBegin(shardingContexts);
}
