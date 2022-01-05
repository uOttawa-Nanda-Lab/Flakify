public void testDeleteDeploymentWithStartSignalEvents7(){
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
  repositoryService.deleteDeployment(deploymentId4,true);
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  cleanup(deploymentId1);
}
