@Test public void assertUpdateLocalInstancePath(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  shutdownListenerManager.new InstanceShutdownStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_UPDATED,"");
  verify(schedulerFacade,times(0)).shutdownInstance();
  JobRegistry.getInstance().shutdown("test_job");
}
