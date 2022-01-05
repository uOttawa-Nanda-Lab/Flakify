@Test public void assertGetShardingItemsWithNotAvailableServer(){
  assertThat(shardingService.getShardingItems("127.0.0.1@-@0"),is(Collections.<Integer>emptyList()));
}
