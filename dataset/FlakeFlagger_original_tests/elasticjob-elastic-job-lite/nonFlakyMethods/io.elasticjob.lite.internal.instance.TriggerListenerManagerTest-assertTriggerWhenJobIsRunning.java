@Test public void assertTriggerWhenJobIsRunning(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  JobRegistry.getInstance().setJobRunning("test_job",true);
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_UPDATED,InstanceOperation.TRIGGER.name());
  verify(instanceService).clearTriggerFlag();
  verify(jobScheduleController,times(0)).triggerJob();
  JobRegistry.getInstance().setJobRunning("test_job",false);
  JobRegistry.getInstance().shutdown("test_job");
}
