@Test public void assertNotTriggerWhenIsNotUpdate(){
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_ADDED,InstanceOperation.TRIGGER.name());
  verify(instanceService,times(0)).clearTriggerFlag();
}
