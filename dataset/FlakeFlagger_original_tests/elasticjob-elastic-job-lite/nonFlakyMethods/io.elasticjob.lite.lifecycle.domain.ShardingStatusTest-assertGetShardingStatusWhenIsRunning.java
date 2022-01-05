@Test public void assertGetShardingStatusWhenIsRunning(){
  assertThat(ShardingInfo.ShardingStatus.getShardingStatus(false,true,false),Is.is(ShardingInfo.ShardingStatus.RUNNING));
}
