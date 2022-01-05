@Test public void assertPersistOnlineForInstanceShutdown(){
  JobRegistry.getInstance().shutdown("test_job");
  serverService.persistOnline(false);
  verify(jobNodeStorage,times(0)).fillJobNode("servers/127.0.0.1",ServerStatus.DISABLED.name());
}
