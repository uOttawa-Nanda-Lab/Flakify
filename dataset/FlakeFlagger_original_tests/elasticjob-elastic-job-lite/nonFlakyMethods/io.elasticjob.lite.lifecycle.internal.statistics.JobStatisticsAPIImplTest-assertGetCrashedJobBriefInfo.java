@Test public void assertGetCrashedJobBriefInfo(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Lists.newArrayList("test_job"));
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getSimpleJobJson("test_job","desc"));
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  JobBriefInfo jobBrief=jobStatisticsAPI.getJobBriefInfo("test_job");
  assertThat(jobBrief.getStatus(),Is.is(JobBriefInfo.JobStatus.CRASHED));
}
