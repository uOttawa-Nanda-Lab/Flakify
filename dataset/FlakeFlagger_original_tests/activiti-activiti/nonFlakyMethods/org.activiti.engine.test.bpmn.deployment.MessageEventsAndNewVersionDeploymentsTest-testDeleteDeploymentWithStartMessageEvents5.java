public void testDeleteDeploymentWithStartMessageEvents5(){
  String deploymentId1=deployStartMessageTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  assertEventSubscriptionsCount(0);
  try {
    runtimeService.startProcessInstanceByMessage("myStartMessage");
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId2,true);
  assertEventSubscriptionsCount(1);
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId1).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId1);
}
