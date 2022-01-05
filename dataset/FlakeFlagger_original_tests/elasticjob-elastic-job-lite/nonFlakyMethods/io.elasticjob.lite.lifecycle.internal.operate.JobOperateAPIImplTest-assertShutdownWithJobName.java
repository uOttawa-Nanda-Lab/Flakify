@Test public void assertShutdownWithJobName(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance"));
  jobOperateAPI.shutdown(Optional.of("test_job"),Optional.<String>absent());
  verify(regCenter).getChildrenKeys("/test_job/instances");
  verify(regCenter).remove("/test_job/instances/ip1@-@defaultInstance");
}
