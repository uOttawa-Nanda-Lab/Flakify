@Test public void assertEnableSharding(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job/sharding")).thenReturn(Arrays.asList("0","1"));
  shardingOperateAPI.enable("test_job","0");
  verify(regCenter).remove("/test_job/sharding/0/disabled");
}
