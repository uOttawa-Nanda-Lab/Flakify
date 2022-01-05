public void testTimerRestoreOnDeploymentDelete2(){
  String deploymentId1=deployTimerProcess();
  String deploymentId2=deployProcessWithoutTimers();
  String deploymentId3=deployTimerProcess();
  String deploymentId4=deployProcessWithoutTimers();
  assertTimerJobs(0);
  repositoryService.deleteDeployment(deploymentId3,true);
  assertTimerJobs(0);
  repositoryService.deleteDeployment(deploymentId2,true);
  assertTimerJobs(0);
  repositoryService.deleteDeployment(deploymentId4,true);
  assertTimerJobs(1);
  Job job=managementService.createTimerJobQuery().singleResult();
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId1).singleResult().getId(),job.getProcessDefinitionId());
  cleanup(deploymentId1);
}
