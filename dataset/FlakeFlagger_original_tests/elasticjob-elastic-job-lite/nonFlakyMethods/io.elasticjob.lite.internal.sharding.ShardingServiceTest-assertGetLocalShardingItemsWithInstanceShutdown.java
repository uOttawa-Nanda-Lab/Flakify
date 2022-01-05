@Test public void assertGetLocalShardingItemsWithInstanceShutdown(){
  assertThat(shardingService.getLocalShardingItems(),is(Collections.<Integer>emptyList()));
}
