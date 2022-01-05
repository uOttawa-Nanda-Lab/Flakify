@Test public void assertShardingNecessaryWhenMonitorExecutionDisabledAndDecreaseShardingTotalCount(){
  when(instanceService.getAvailableJobInstances()).thenReturn(Collections.singletonList(new JobInstance("127.0.0.1@-@0")));
  when(jobNodeStorage.isJobNodeExisted("leader/sharding/necessary")).thenReturn(true);
  when(leaderService.isLeaderUntilBlock()).thenReturn(true);
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).monitorExecution(false).build());
  when(jobNodeStorage.getJobNodeChildrenKeys(ShardingNode.ROOT)).thenReturn(Arrays.asList("0","1","2","3"));
  shardingService.shardingIfNecessary();
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/instance");
  verify(jobNodeStorage).createJobNodeIfNeeded("sharding/0");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/instance");
  verify(jobNodeStorage).createJobNodeIfNeeded("sharding/1");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/2/instance");
  verify(jobNodeStorage).createJobNodeIfNeeded("sharding/2");
  verify(jobNodeStorage,times(0)).removeJobNodeIfExisted("execution/2");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/3");
  verify(jobNodeStorage).fillEphemeralJobNode("leader/sharding/processing","");
  verify(jobNodeStorage).executeInTransaction(any(TransactionExecutionCallback.class));
}
