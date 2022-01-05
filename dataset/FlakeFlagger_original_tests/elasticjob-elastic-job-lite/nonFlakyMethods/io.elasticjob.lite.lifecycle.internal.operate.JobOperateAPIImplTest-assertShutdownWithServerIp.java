@Test public void assertShutdownWithServerIp(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job1","test_job2"));
  when(regCenter.getChildrenKeys("/test_job1/instances")).thenReturn(Collections.singletonList("localhost@-@defaultInstance"));
  when(regCenter.getChildrenKeys("/test_job2/instances")).thenReturn(Collections.singletonList("localhost@-@defaultInstance"));
  jobOperateAPI.shutdown(Optional.<String>absent(),Optional.of("localhost"));
  verify(regCenter).getChildrenKeys("/");
  verify(regCenter).remove("/test_job1/instances/localhost@-@defaultInstance");
  verify(regCenter).remove("/test_job2/instances/localhost@-@defaultInstance");
}
