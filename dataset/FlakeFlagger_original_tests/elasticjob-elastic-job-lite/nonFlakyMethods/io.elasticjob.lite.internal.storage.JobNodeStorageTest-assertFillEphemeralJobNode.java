@Test public void assertFillEphemeralJobNode(){
  jobNodeStorage.fillEphemeralJobNode("config/cron","0/1 * * * * ?");
  verify(regCenter).persistEphemeral("/test_job/config/cron","0/1 * * * * ?");
}
