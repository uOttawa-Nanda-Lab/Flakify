@Test public void assertConnectionLostListenerWhenConnectionStateIsOther(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  regCenterConnectionStateListener.stateChanged(null,ConnectionState.CONNECTED);
  verify(jobScheduleController,times(0)).pauseJob();
  verify(jobScheduleController,times(0)).resumeJob();
  JobRegistry.getInstance().shutdown("test_job");
}
