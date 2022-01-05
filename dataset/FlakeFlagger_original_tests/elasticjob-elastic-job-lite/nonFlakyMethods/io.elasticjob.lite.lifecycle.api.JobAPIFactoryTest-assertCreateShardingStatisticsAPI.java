@Test public void assertCreateShardingStatisticsAPI(){
  assertThat(JobAPIFactory.createShardingStatisticsAPI(getConnectionString(),"namespace",Optional.<String>absent()),instanceOf(ShardingStatisticsAPI.class));
}
