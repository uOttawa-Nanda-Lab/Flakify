@Test public void assertGetAllJobsBriefInfoWithoutNamespace(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2"));
  assertThat(jobStatisticsAPI.getAllJobsBriefInfo().size(),is(0));
}
