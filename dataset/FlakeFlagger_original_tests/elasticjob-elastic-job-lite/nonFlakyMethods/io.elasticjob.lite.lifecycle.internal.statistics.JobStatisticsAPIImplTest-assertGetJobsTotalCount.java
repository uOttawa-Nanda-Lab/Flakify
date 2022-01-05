@Test public void assertGetJobsTotalCount(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2"));
  assertThat(jobStatisticsAPI.getJobsTotalCount(),is(2));
}
