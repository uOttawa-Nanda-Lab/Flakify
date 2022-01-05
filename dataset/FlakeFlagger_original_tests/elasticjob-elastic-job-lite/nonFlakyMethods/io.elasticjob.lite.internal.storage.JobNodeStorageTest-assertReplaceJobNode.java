@Test public void assertReplaceJobNode(){
  jobNodeStorage.replaceJobNode("config/cron","0/1 * * * * ?");
  verify(regCenter).persist("/test_job/config/cron","0/1 * * * * ?");
}
