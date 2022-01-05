@Test public void assertGetJobsBriefInfoByIp(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2","test_job_3"));
  when(regCenter.getChildrenKeys("/test_job_1/servers")).thenReturn(Collections.singletonList("ip1"));
  when(regCenter.getChildrenKeys("/test_job_2/servers")).thenReturn(Collections.singletonList("ip1"));
  when(regCenter.getChildrenKeys("/test_job_3/servers")).thenReturn(Collections.singletonList("ip1"));
  when(regCenter.isExisted("/test_job_1/servers/ip1")).thenReturn(true);
  when(regCenter.isExisted("/test_job_2/servers/ip1")).thenReturn(true);
  when(regCenter.get("/test_job_2/servers/ip1")).thenReturn("DISABLED");
  when(regCenter.getChildrenKeys("/test_job_1/instances")).thenReturn(Collections.singletonList("ip1@-@defaultInstance"));
  int i=0;
  for (  JobBriefInfo each : jobStatisticsAPI.getJobsBriefInfo("ip1")) {
    assertThat(each.getJobName(),is("test_job_" + ++i));
    if (i == 1) {
      assertThat(each.getInstanceCount(),is(1));
      assertThat(each.getStatus(),Is.is(JobBriefInfo.JobStatus.OK));
    }
 else     if (i == 2) {
      assertThat(each.getInstanceCount(),is(0));
      assertThat(each.getStatus(),Is.is(JobBriefInfo.JobStatus.DISABLED));
    }
  }
}
