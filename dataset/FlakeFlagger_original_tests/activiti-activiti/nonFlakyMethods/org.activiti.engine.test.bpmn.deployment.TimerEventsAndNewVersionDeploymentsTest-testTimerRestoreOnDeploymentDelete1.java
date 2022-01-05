public void testTimerRestoreOnDeploymentDelete1(){
  String deploymentId1=deployTimerProcess();
  String deploymentId2=deployProcessWithoutTimers();
  String deploymentId3=deployTimerProcess();
  String deploymentId4=deployProcessWithoutTimers();
  assertTimerJobs(0);
  repositoryService.deleteDeployment(deploymentId4,true);
  assertTimerJobs(1);
  Job job=managementService.createTimerJobQuery().singleResult();
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),job.getProcessDefinitionId());
  cleanup(deploymentId1,deploymentId2,deploymentId3);
}
