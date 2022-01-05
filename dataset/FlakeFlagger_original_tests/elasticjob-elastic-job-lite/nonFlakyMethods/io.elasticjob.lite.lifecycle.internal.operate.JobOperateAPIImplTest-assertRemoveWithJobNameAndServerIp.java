@Test public void assertRemoveWithJobNameAndServerIp(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  jobOperateAPI.remove(Optional.of("test_job"),Optional.of("ip1"));
  verify(regCenter).remove("/test_job/servers/ip1");
  assertFalse(regCenter.isExisted("/test_job/servers/ip1"));
}
