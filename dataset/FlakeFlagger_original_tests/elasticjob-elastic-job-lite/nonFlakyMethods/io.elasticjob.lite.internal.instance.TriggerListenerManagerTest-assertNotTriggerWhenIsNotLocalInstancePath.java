@Test public void assertNotTriggerWhenIsNotLocalInstancePath(){
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.2@-@0",Type.NODE_UPDATED,InstanceOperation.TRIGGER.name());
  verify(instanceService,times(0)).clearTriggerFlag();
}
