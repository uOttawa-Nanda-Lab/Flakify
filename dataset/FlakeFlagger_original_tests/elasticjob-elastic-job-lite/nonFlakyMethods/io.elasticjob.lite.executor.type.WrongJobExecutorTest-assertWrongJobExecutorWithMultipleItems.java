@Test public void assertWrongJobExecutorWithMultipleItems() throws NoSuchFieldException {
  Map<Integer,String> map=new HashMap<>(1,1);
  map.put(0,"A");
  map.put(1,"B");
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",map);
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  wrongSimpleJobExecutor.execute();
  verify(jobFacade).getShardingContexts();
  verify(jobFacade).postJobStatusTraceEvent("fake_task_id",State.TASK_RUNNING,"");
}
