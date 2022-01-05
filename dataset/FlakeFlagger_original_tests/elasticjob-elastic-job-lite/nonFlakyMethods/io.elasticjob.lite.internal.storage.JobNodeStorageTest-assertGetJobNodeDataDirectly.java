@Test public void assertGetJobNodeDataDirectly(){
  when(regCenter.getDirectly("/test_job/config/cron")).thenReturn("0/1 * * * * ?");
  assertThat(jobNodeStorage.getJobNodeDataDirectly("config/cron"),is("0/1 * * * * ?"));
  verify(regCenter).getDirectly("/test_job/config/cron");
}
