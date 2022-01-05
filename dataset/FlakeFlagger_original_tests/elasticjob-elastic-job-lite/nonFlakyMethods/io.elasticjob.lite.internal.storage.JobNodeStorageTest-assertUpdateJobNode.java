@Test public void assertUpdateJobNode(){
  jobNodeStorage.updateJobNode("config/cron","0/1 * * * * ?");
  verify(regCenter).update("/test_job/config/cron","0/1 * * * * ?");
}
