public void testTimerCreationOnNewDeployments(){
  String deploymentId1=deployTimerProcess();
  assertTimerJobs(1);
  String deploymentId2=deployTimerProcess();
  assertTimerJobs(1);
  String deploymentId3=deployProcessWithoutTimers();
  assertTimerJobs(0);
  String deploymentId4=deployTimerProcess();
  assertTimerJobs(1);
  cleanup(deploymentId1,deploymentId2,deploymentId3,deploymentId4);
}
