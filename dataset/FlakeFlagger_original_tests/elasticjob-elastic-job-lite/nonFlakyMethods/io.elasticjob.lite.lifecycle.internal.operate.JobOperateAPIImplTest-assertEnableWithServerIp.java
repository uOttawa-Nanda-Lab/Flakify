@Test public void assertEnableWithServerIp(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job1","test_job2"));
  when(regCenter.isExisted("/test_job1/servers/localhost")).thenReturn(true);
  when(regCenter.isExisted("/test_job2/servers/localhost")).thenReturn(true);
  jobOperateAPI.enable(Optional.<String>absent(),Optional.of("localhost"));
  verify(regCenter).getChildrenKeys("/");
  verify(regCenter).persist("/test_job1/servers/localhost","");
  verify(regCenter).persist("/test_job2/servers/localhost","");
}
