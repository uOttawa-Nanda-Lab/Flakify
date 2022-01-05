@Test public void assertTriggerWhenJobIsNotRunning(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_UPDATED,InstanceOperation.TRIGGER.name());
  verify(instanceService).clearTriggerFlag();
  verify(jobScheduleController).triggerJob();
  JobRegistry.getInstance().shutdown("test_job");
}
