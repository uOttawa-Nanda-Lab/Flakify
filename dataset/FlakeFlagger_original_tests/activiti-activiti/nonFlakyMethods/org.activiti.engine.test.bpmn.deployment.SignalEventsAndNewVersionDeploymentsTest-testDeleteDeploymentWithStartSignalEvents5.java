public void testDeleteDeploymentWithStartSignalEvents5(){
  String deploymentId1=deployStartSignalTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  assertEventSubscriptionsCount(0);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId2,true);
  assertEventSubscriptionsCount(1);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId1).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId1);
}
