@Test public void assertRemoveWithServerIp(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job1","test_job2"));
  when(regCenter.getChildrenKeys("/test_job1/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job2/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  jobOperateAPI.remove(Optional.<String>absent(),Optional.of("ip1"));
  assertFalse(regCenter.isExisted("/test_job1/servers/ip1"));
  assertFalse(regCenter.isExisted("/test_job2/servers/ip1"));
}
