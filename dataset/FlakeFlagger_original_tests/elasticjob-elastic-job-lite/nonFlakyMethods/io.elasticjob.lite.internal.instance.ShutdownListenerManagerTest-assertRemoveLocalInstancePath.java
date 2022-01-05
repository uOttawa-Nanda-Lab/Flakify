@Test public void assertRemoveLocalInstancePath(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  shutdownListenerManager.new InstanceShutdownStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(schedulerFacade).shutdownInstance();
}
