@Test public void assertEnableWithJobName(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  jobOperateAPI.enable(Optional.of("test_job"),Optional.<String>absent());
  verify(regCenter).getChildrenKeys("/test_job/servers");
  verify(regCenter).persist("/test_job/servers/ip1","");
  verify(regCenter).persist("/test_job/servers/ip2","");
}
