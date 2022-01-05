@Test public void assertGetShardingStatusWhenIsShardingError(){
  assertThat(ShardingInfo.ShardingStatus.getShardingStatus(false,false,true),Is.is(ShardingInfo.ShardingStatus.SHARDING_FLAG));
}
