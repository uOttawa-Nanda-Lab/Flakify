@Test public void assertShutdownInstanceIfLeaderAndReconcileServiceIsRunning(){
  when(leaderService.isLeader()).thenReturn(true);
  when(reconcileService.isRunning()).thenReturn(true);
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  schedulerFacade.shutdownInstance();
  verify(leaderService).removeLeader();
  verify(monitorService).close();
  verify(reconcileService).stopAsync();
  verify(jobScheduleController).shutdown();
}
