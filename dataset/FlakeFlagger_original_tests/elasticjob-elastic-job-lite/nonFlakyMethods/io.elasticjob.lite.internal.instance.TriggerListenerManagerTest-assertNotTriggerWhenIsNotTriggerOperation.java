@Test public void assertNotTriggerWhenIsNotTriggerOperation(){
  triggerListenerManager.new JobTriggerStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_UPDATED,"");
  verify(instanceService,times(0)).clearTriggerFlag();
}
