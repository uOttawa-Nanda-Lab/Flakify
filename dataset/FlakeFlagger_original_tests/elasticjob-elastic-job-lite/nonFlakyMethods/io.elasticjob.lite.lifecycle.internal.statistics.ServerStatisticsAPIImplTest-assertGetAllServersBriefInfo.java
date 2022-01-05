@Test public void assertGetAllServersBriefInfo(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job1","test_job2"));
  when(regCenter.getChildrenKeys("/test_job1/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job2/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.get("/test_job1/servers/ip1")).thenReturn("DISABLED");
  when(regCenter.get("/test_job1/servers/ip2")).thenReturn("");
  when(regCenter.getChildrenKeys("/test_job1/instances")).thenReturn(Collections.singletonList("ip1@-@defaultInstance"));
  when(regCenter.get("/test_job2/servers/ip1")).thenReturn("DISABLED");
  when(regCenter.get("/test_job2/servers/ip2")).thenReturn("DISABLED");
  when(regCenter.getChildrenKeys("/test_job2/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance2"));
  int i=0;
  for (  ServerBriefInfo each : serverStatisticsAPI.getAllServersBriefInfo()) {
    i++;
    assertThat(each.getServerIp(),is("ip" + i));
switch (i) {
case 1:
      assertThat(each.getDisabledJobsNum().intValue(),is(2));
    assertThat(each.getJobsNum(),is(2));
  assertThat(each.getInstancesNum(),is(1));
break;
case 2:
assertThat(each.getDisabledJobsNum().intValue(),is(1));
assertThat(each.getJobsNum(),is(2));
assertThat(each.getInstancesNum(),is(1));
break;
default :
fail();
}
}
}
