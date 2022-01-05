@Test public void assertConnectionLostListenerWhenConnectionStateIsLost(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  regCenterConnectionStateListener.stateChanged(null,ConnectionState.LOST);
  verify(jobScheduleController).pauseJob();
  JobRegistry.getInstance().shutdown("test_job");
}
