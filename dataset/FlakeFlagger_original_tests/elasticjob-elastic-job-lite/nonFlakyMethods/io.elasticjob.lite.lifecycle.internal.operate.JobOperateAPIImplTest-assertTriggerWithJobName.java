@Test public void assertTriggerWithJobName(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance"));
  jobOperateAPI.trigger(Optional.of("test_job"),Optional.<String>absent());
  verify(regCenter).getChildrenKeys("/test_job/instances");
  verify(regCenter).persist("/test_job/instances/ip1@-@defaultInstance","TRIGGER");
  verify(regCenter).persist("/test_job/instances/ip2@-@defaultInstance","TRIGGER");
}
