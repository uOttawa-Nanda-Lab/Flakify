@Test public void assertGetShardingContextWhenHasRunningItems(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).shardingItemParameters("0=A,1=B,2=C").build(),TestDataflowJob.class.getCanonicalName(),true)).monitorExecution(true).build());
  when(jobNodeStorage.isJobNodeExisted("sharding/0/running")).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/running")).thenReturn(true);
  Map<Integer,String> map=new HashMap<>(1,1);
  map.put(0,"A");
  ShardingContexts expected=new ShardingContexts("fake_task_id","test_job",3,"",map);
  assertShardingContext(executionContextService.getJobShardingContext(Lists.newArrayList(0,1)),expected);
}
