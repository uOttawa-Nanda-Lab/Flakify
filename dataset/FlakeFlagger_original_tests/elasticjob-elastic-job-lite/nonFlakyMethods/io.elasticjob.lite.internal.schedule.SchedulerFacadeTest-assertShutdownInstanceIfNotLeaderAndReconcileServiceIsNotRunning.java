@Test public void assertShutdownInstanceIfNotLeaderAndReconcileServiceIsNotRunning(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  schedulerFacade.shutdownInstance();
  verify(leaderService,times(0)).removeLeader();
  verify(monitorService).close();
  verify(reconcileService,times(0)).stopAsync();
  verify(jobScheduleController).shutdown();
}
