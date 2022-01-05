@Test public void assertGetShardingErrorJobBriefInfo(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Lists.newArrayList("test_job"));
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getSimpleJobJson("test_job","desc"));
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance"));
  when(regCenter.getChildrenKeys("/test_job/sharding")).thenReturn(Arrays.asList("0","1","2"));
  when(regCenter.get("/test_job/sharding/0/instance")).thenReturn("ip1@-@defaultInstance");
  when(regCenter.get("/test_job/sharding/1/instance")).thenReturn("ip2@-@defaultInstance");
  when(regCenter.get("/test_job/sharding/2/instance")).thenReturn("ip3@-@defaultInstance");
  JobBriefInfo jobBrief=jobStatisticsAPI.getJobBriefInfo("test_job");
  assertThat(jobBrief.getStatus(),Is.is(JobBriefInfo.JobStatus.SHARDING_FLAG));
}
