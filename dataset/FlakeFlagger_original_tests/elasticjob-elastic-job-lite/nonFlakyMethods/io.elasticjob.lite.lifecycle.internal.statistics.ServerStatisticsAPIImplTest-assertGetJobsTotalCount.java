@Test public void assertGetJobsTotalCount(){
  when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2"));
  when(regCenter.getChildrenKeys("/test_job_1/servers")).thenReturn(Arrays.asList("ip1","ip2"));
  when(regCenter.getChildrenKeys("/test_job_2/servers")).thenReturn(Arrays.asList("ip2","ip3"));
  assertThat(serverStatisticsAPI.getServersTotalCount(),is(3));
}
