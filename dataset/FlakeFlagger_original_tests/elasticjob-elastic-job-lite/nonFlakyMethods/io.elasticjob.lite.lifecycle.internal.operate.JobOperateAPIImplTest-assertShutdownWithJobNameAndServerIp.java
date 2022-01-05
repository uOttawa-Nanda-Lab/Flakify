@Test public void assertShutdownWithJobNameAndServerIp(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Collections.singletonList("localhost"));
  when(regCenter.getChildrenKeys("/test_job/instances")).thenReturn(Collections.singletonList("localhost@-@defaultInstance"));
  jobOperateAPI.shutdown(Optional.of("test_job"),Optional.of("localhost"));
  verify(regCenter).remove("/test_job/instances/localhost@-@defaultInstance");
}
