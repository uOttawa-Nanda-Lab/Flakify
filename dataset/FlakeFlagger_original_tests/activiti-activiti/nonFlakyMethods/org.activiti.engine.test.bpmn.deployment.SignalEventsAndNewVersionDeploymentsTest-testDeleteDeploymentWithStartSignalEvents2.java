public void testDeleteDeploymentWithStartSignalEvents2(){
  String deploymentId1=deployStartSignalTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartSignalTestProcess();
  repositoryService.deleteDeployment(deploymentId2,true);
  assertEventSubscriptionsCount(1);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId1,deploymentId3);
}
