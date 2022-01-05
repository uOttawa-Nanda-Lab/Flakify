public void testStartSignalEvent(){
  String deploymentId1=deployStartSignalTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  String deploymentId2=deployStartSignalTestProcess();
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,getAllEventSubscriptions().size());
  cleanup(deploymentId1,deploymentId2);
}
