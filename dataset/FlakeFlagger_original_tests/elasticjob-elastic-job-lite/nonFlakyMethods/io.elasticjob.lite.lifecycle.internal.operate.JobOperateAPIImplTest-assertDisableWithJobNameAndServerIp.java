@Test public void assertDisableWithJobNameAndServerIp(){
  jobOperateAPI.disable(Optional.of("test_job"),Optional.of("localhost"));
  verify(regCenter).persist("/test_job/servers/localhost","DISABLED");
}
