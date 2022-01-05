@Test public void assertGetShardingInfo(){
  when(regCenter.isExisted("/test_job/sharding")).thenReturn(true);
  when(regCenter.getChildrenKeys("/test_job/sharding")).thenReturn(Arrays.asList("0","1","2","3"));
  when(regCenter.get("/test_job/sharding/0/instance")).thenReturn("ip1@-@1234");
  when(regCenter.get("/test_job/sharding/1/instance")).thenReturn("ip2@-@2341");
  when(regCenter.get("/test_job/sharding/2/instance")).thenReturn("ip3@-@3412");
  when(regCenter.get("/test_job/sharding/3/instance")).thenReturn("ip4@-@4123");
  when(regCenter.isExisted("/test_job/instances/ip4@-@4123")).thenReturn(true);
  when(regCenter.isExisted("/test_job/sharding/0/running")).thenReturn(true);
  when(regCenter.isExisted("/test_job/sharding/1/running")).thenReturn(false);
  when(regCenter.isExisted("/test_job/sharding/2/running")).thenReturn(false);
  when(regCenter.isExisted("/test_job/sharding/3/running")).thenReturn(false);
  when(regCenter.isExisted("/test_job/sharding/0/failover")).thenReturn(false);
  when(regCenter.isExisted("/test_job/sharding/1/failover")).thenReturn(true);
  when(regCenter.isExisted("/test_job/sharding/2/disabled")).thenReturn(true);
  int i=0;
  for (  ShardingInfo each : shardingStatisticsAPI.getShardingInfo("test_job")) {
    i++;
    assertThat(each.getItem(),is(i - 1));
switch (i) {
case 1:
      assertThat(each.getStatus(),is(ShardingInfo.ShardingStatus.RUNNING));
    assertThat(each.getServerIp(),is("ip1"));
  assertThat(each.getInstanceId(),is("1234"));
break;
case 2:
assertTrue(each.isFailover());
assertThat(each.getStatus(),is(ShardingInfo.ShardingStatus.SHARDING_FLAG));
assertThat(each.getServerIp(),is("ip2"));
assertThat(each.getInstanceId(),is("2341"));
break;
case 3:
assertThat(each.getStatus(),Is.is(ShardingInfo.ShardingStatus.DISABLED));
assertThat(each.getServerIp(),is("ip3"));
assertThat(each.getInstanceId(),is("3412"));
break;
case 4:
assertThat(each.getStatus(),Is.is(ShardingInfo.ShardingStatus.PENDING));
assertThat(each.getServerIp(),is("ip4"));
assertThat(each.getInstanceId(),is("4123"));
break;
default :
break;
}
}
}
