@Test public void assertRemoveWithJobName(){
  when(regCenter.isExisted("/test_job")).thenReturn(true);
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  jobOperateAPI.remove(Optional.of("test_job"),Optional.<String>absent());
  verify(regCenter).getChildrenKeys("/test_job/servers");
  verify(regCenter).remove("/test_job/servers/ip1");
  verify(regCenter).remove("/test_job/servers/ip2");
  assertFalse(regCenter.isExisted("/test_job/servers/ip1"));
  assertFalse(regCenter.isExisted("/test_job/servers/ip2"));
  assertTrue(regCenter.isExisted("/test_job"));
}
