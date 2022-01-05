@Test public void assertConnectionLostListenerWhenConnectionStateIsReconnected(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(shardingService.getLocalShardingItems()).thenReturn(Arrays.asList(0,1));
  when(serverService.isEnableServer("127.0.0.1")).thenReturn(true);
  regCenterConnectionStateListener.stateChanged(null,ConnectionState.RECONNECTED);
  verify(serverService).persistOnline(true);
  verify(executionService).clearRunningInfo(Arrays.asList(0,1));
  verify(jobScheduleController).resumeJob();
  JobRegistry.getInstance().shutdown("test_job");
}
