@Test public void assertIsNotLocalInstancePath(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  shutdownListenerManager.new InstanceShutdownStatusJobListener().dataChanged("/test_job/instances/127.0.0.2@-@0",Type.NODE_REMOVED,"");
  verify(schedulerFacade,times(0)).shutdownInstance();
  JobRegistry.getInstance().shutdown("test_job");
}
