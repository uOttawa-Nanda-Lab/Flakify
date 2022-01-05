@Test public void assertConnectionLostListenerWhenConnectionStateIsReconnectedButIsShutdown(){
  when(shardingService.getLocalShardingItems()).thenReturn(Arrays.asList(0,1));
  when(serverService.isEnableServer("127.0.0.1")).thenReturn(true);
  regCenterConnectionStateListener.stateChanged(null,ConnectionState.RECONNECTED);
  verify(jobScheduleController,times(0)).pauseJob();
  verify(jobScheduleController,times(0)).resumeJob();
}
