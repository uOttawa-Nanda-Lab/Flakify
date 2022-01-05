@Test public void assertShardingWithoutAvailableJobInstances(){
  when(jobNodeStorage.isJobNodeExisted("leader/sharding/necessary")).thenReturn(true);
  shardingService.shardingIfNecessary();
  verify(jobNodeStorage,times(0)).fillEphemeralJobNode(ShardingNode.PROCESSING,"");
}
