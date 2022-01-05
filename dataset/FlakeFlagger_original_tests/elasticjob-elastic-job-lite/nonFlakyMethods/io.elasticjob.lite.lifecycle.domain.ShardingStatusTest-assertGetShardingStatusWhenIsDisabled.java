@Test public void assertGetShardingStatusWhenIsDisabled(){
  assertThat(ShardingInfo.ShardingStatus.getShardingStatus(true,false,true),Is.is(ShardingInfo.ShardingStatus.DISABLED));
}
