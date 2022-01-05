@Test public void assertGetAllJobsBriefInfo(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2"));
  when(regCenter.get("/test_job_1/config")).thenReturn(LifecycleJsonConstants.getSimpleJobJson("test_job_1","desc1"));
  when(regCenter.get("/test_job_2/config")).thenReturn(LifecycleJsonConstants.getSimpleJobJson("test_job_2","desc2"));
  when(regCenter.getChildrenKeys("/test_job_1/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job_2/servers")).thenReturn(Arrays.asList("ip3","ip4"));
  when(regCenter.getChildrenKeys("/test_job_1/sharding")).thenReturn(Arrays.asList("0","1"));
  when(regCenter.get("/test_job_1/sharding/0/instance")).thenReturn("ip1@-@defaultInstance");
  when(regCenter.get("/test_job_1/sharding/1/instance")).thenReturn("ip2@-@defaultInstance");
  when(regCenter.getChildrenKeys("/test_job_2/sharding")).thenReturn(Arrays.asList("0","1"));
  when(regCenter.get("/test_job_2/sharding/0/instance")).thenReturn("ip3@-@defaultInstance");
  when(regCenter.get("/test_job_2/sharding/1/instance")).thenReturn("ip4@-@defaultInstance");
  when(regCenter.getChildrenKeys("/test_job_1/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance"));
  when(regCenter.getChildrenKeys("/test_job_2/instances")).thenReturn(Arrays.asList("ip3@-@defaultInstance","ip4@-@defaultInstance"));
  int i=0;
  for (  JobBriefInfo each : jobStatisticsAPI.getAllJobsBriefInfo()) {
    i++;
    assertThat(each.getJobName(),is("test_job_" + i));
    assertThat(each.getDescription(),is("desc" + i));
    assertThat(each.getCron(),is("0/1 * * * * ?"));
    assertThat(each.getInstanceCount(),is(2));
    assertThat(each.getShardingTotalCount(),is(3));
    assertThat(each.getStatus(),Is.is(JobBriefInfo.JobStatus.OK));
  }
}
