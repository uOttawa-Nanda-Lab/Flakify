@Test public void assertTriggerWhenJobScheduleControllerIsNull(){
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_UPDATED,InstanceOperation.TRIGGER.name());
  verify(instanceService).clearTriggerFlag();
  verify(jobScheduleController,times(0)).triggerJob();
}
