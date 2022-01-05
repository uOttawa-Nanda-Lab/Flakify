@Test public void assertConnectionLostListenerWhenConnectionStateIsLostButIsShutdown(){
  regCenterConnectionStateListener.stateChanged(null,ConnectionState.LOST);
  verify(jobScheduleController,times(0)).pauseJob();
  verify(jobScheduleController,times(0)).resumeJob();
}
