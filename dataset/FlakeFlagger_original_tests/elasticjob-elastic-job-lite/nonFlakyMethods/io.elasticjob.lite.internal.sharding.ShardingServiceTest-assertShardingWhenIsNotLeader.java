@Test public void assertShardingWhenIsNotLeader(){
  when(jobNodeStorage.isJobNodeExisted("leader/sharding/necessary")).thenReturn(true,false);
  when(instanceService.getAvailableJobInstances()).thenReturn(Collections.singletonList(new JobInstance("127.0.0.1@-@0")));
  when(leaderService.isLeaderUntilBlock()).thenReturn(false);
  when(jobNodeStorage.isJobNodeExisted("leader/sharding/processing")).thenReturn(true,false);
  shardingService.shardingIfNecessary();
  verify(jobNodeStorage,times(0)).fillEphemeralJobNode(ShardingNode.PROCESSING,"");
}
