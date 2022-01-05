@Test public void assertCreateServerOperateAPI(){
  assertThat(JobAPIFactory.createShardingOperateAPI(getConnectionString(),"namespace",Optional.<String>absent()),instanceOf(ShardingOperateAPI.class));
}
