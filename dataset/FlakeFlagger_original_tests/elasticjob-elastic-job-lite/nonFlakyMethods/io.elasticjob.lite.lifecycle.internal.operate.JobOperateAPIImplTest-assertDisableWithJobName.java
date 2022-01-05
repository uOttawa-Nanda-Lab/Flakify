@Test public void assertDisableWithJobName(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  jobOperateAPI.disable(Optional.of("test_job"),Optional.<String>absent());
  verify(regCenter).getChildrenKeys("/test_job/servers");
  verify(regCenter).persist("/test_job/servers/ip1","DISABLED");
  verify(regCenter).persist("/test_job/servers/ip2","DISABLED");
}
