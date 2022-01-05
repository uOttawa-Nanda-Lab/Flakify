@Test public void assertFillJobNode(){
  jobNodeStorage.fillJobNode("config/cron","0/1 * * * * ?");
  verify(regCenter).persist("/test_job/config/cron","0/1 * * * * ?");
}
