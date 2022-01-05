public void testDeleteDeploymentWithStartMessageEvents3(){
  String deploymentId1=deployStartMessageTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartMessageTestProcess();
  repositoryService.deleteDeployment(deploymentId1,true);
  assertEventSubscriptionsCount(1);
  runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),runtimeService.createProcessInstanceQuery().singleResult().getProcessDefinitionId());
  cleanup(deploymentId2,deploymentId3);
}
