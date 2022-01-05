@Test public void assertGetJobNodeData(){
  when(regCenter.get("/test_job/config/cron")).thenReturn("0/1 * * * * ?");
  assertThat(jobNodeStorage.getJobNodeData("config/cron"),is("0/1 * * * * ?"));
  verify(regCenter).get("/test_job/config/cron");
}
