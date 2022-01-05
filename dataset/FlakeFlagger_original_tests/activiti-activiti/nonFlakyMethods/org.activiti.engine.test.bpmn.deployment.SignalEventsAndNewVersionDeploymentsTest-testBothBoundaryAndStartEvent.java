public void testBothBoundaryAndStartEvent(){
  String deploymentId1=deployProcessWithBothStartAndBoundarySignal();
  assertEventSubscriptionsCount(1);
  assertEquals(0,runtimeService.createExecutionQuery().count());
  runtimeService.signalEventReceived("myStartSignal");
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEquals(3,getAllEventSubscriptions().size());
  String deploymentId2=deployBoundarySignalTestProcess();
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  String deploymentId3=deployStartSignalTestProcess();
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(3);
  repositoryService.deleteDeployment(deploymentId3,true);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  runtimeService.signalEventReceived("myBoundarySignal");
  assertEquals(2,taskService.createTaskQuery().taskName("Task after boundary signal").list().size());
  repositoryService.deleteDeployment(deploymentId2,true);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  cleanup(deploymentId1);
}
