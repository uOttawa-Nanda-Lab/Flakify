@Test public void assertPersistOnlineForDisabledServer(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  serverService.persistOnline(false);
  verify(jobNodeStorage).fillJobNode("servers/127.0.0.1",ServerStatus.DISABLED.name());
  JobRegistry.getInstance().shutdown("test_job");
}
