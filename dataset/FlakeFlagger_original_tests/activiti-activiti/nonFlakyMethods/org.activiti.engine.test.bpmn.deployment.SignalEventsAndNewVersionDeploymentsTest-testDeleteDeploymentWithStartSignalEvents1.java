public void testDeleteDeploymentWithStartSignalEvents1(){
  String deploymentId1, deploymentId2, deploymentId3;
  deploymentId1=deployStartSignalTestProcess();
  deploymentId2=deployProcessWithoutEvents();
  deploymentId3=deployStartSignalTestProcess();
  repositoryService.deleteDeployment(deploymentId3,true);
  assertEventSubscriptionsCount(0);
  cleanup(deploymentId1,deploymentId2);
}
