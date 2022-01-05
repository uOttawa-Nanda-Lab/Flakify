public void testDeleteDeploymentWithStartSignalEvents6(){
  String deploymentId1=deployStartSignalTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartSignalTestProcess();
  String deploymentId4=deployProcessWithoutEvents();
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId2,true);
  repositoryService.deleteDeployment(deploymentId3,true);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId1,true);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(0,runtimeService.createExecutionQuery().count());
  cleanup(deploymentId4);
}
