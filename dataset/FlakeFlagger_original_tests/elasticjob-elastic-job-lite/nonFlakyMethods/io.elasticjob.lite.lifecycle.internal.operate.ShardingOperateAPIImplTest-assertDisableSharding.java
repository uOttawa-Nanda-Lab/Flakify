@Test public void assertDisableSharding(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job/sharding")).thenReturn(Arrays.asList("0","1"));
  shardingOperateAPI.disable("test_job","0");
  verify(regCenter).persist("/test_job/sharding/0/disabled","");
}
