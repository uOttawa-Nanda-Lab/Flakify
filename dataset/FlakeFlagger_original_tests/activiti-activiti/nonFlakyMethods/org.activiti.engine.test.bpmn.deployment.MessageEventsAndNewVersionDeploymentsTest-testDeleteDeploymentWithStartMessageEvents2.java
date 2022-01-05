public void testDeleteDeploymentWithStartMessageEvents2(){
  String deploymentId1=deployStartMessageTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartMessageTestProcess();
  repositoryService.deleteDeployment(deploymentId2,true);
  assertEventSubscriptionsCount(1);
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId1,deploymentId3);
}
