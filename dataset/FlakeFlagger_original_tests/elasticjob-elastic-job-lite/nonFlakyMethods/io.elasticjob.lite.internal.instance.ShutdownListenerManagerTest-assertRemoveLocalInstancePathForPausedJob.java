@Test public void assertRemoveLocalInstancePathForPausedJob(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(jobScheduleController.isPaused()).thenReturn(true);
  shutdownListenerManager.new InstanceShutdownStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(schedulerFacade,times(0)).shutdownInstance();
}
