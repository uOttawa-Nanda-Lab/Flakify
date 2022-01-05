@Test public void assertGetLocalTakeOffItems(){
  when(shardingService.getLocalShardingItems()).thenReturn(Arrays.asList(0,1,2));
  when(jobNodeStorage.isJobNodeExisted("sharding/0/failover")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/failover")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/2/failover")).thenReturn(false);
  assertThat(failoverService.getLocalTakeOffItems(),is(Arrays.asList(0,1)));
  verify(shardingService).getLocalShardingItems();
  verify(jobNodeStorage).isJobNodeExisted("sharding/0/failover");
  verify(jobNodeStorage).isJobNodeExisted("sharding/1/failover");
  verify(jobNodeStorage).isJobNodeExisted("sharding/2/failover");
}
