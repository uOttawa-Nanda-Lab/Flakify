public void testBothBoundaryAndStartEvent(){
  String deploymentId1=deployProcessWithBothStartAndBoundaryMessage();
  assertEventSubscriptionsCount(1);
  assertEquals(0,runtimeService.createExecutionQuery().count());
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEquals(3,getAllEventSubscriptions().size());
  String deploymentId2=deployBoundaryMessageTestProcess();
  try {
    runtimeService.startProcessInstanceByMessage("myStartMessage");
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  String deploymentId3=deployStartMessageTestProcess();
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(3);
  repositoryService.deleteDeployment(deploymentId3,true);
  try {
    runtimeService.startProcessInstanceByMessage("myStartMessage");
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  assertReceiveMessage("myBoundaryMessage",2);
  assertEquals(2,taskService.createTaskQuery().taskName("Task after boundary message").list().size());
  repositoryService.deleteDeployment(deploymentId2,true);
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(2);
  cleanup(deploymentId1);
}
