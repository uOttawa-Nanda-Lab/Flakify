public void testDeleteDeploymentWithStartMessageEvents4(){
  String deploymentId1=deployStartMessageTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartMessageTestProcess();
  repositoryService.deleteDeployment(deploymentId2,true);
  repositoryService.deleteDeployment(deploymentId3,true);
  assertEventSubscriptionsCount(1);
  runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId1).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId1);
}
