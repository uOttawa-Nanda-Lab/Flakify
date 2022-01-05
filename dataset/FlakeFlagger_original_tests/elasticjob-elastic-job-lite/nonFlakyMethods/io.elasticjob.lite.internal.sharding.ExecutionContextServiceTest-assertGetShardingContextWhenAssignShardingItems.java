@Test public void assertGetShardingContextWhenAssignShardingItems(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).shardingItemParameters("0=A,1=B,2=C").build(),TestDataflowJob.class.getCanonicalName(),true)).monitorExecution(false).build());
  Map<Integer,String> map=new HashMap<>(3);
  map.put(0,"A");
  map.put(1,"B");
  ShardingContexts expected=new ShardingContexts("fake_task_id","test_job",3,"",map);
  assertShardingContext(executionContextService.getJobShardingContext(Arrays.asList(0,1)),expected);
}
