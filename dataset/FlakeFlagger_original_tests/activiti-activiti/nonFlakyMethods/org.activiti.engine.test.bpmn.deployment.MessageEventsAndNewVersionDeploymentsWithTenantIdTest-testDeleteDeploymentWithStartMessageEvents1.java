public void testDeleteDeploymentWithStartMessageEvents1(){
  String deploymentId1, deploymentId2, deploymentId3;
  deploymentId1=deployStartMessageTestProcess();
  deploymentId2=deployProcessWithoutEvents();
  deploymentId3=deployStartMessageTestProcess();
  repositoryService.deleteDeployment(deploymentId3,true);
  assertEventSubscriptionsCount(0);
  cleanup(deploymentId1,deploymentId2);
}
