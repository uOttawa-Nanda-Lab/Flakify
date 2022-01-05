@Test public void assertToString(){
  assertThat(new ShardingContext(ShardingContextsBuilder.getMultipleShardingContexts(),1).toString(),is("ShardingContext(jobName=test_job, taskId=fake_task_id, shardingTotalCount=2, jobParameter=, shardingItem=1, shardingParameter=B)"));
}
