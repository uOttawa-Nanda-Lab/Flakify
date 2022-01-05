@Test(expected=RuntimeException.class) public void assertWrongJobExecutorWithSingleItem() throws NoSuchFieldException {
  Map<Integer,String> map=new HashMap<>(1,1);
  map.put(0,"A");
  ShardingContexts shardingContexts=new ShardingContexts("fake_task_id","test_job",10,"",map);
  when(jobFacade.getShardingContexts()).thenReturn(shardingContexts);
  wrongSimpleJobExecutor.execute();
}
