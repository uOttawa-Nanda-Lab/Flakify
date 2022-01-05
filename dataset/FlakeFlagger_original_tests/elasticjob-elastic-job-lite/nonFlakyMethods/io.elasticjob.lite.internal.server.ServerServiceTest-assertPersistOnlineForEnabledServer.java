@Test public void assertPersistOnlineForEnabledServer(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  serverService.persistOnline(true);
  verify(jobNodeStorage).fillJobNode("servers/127.0.0.1","");
  JobRegistry.getInstance().shutdown("test_job");
}
