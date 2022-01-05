public void testDeleteDeploymentWithStartMessageEvents6(){
  String deploymentId1=deployStartMessageTestProcess();
  String deploymentId2=deployProcessWithoutEvents();
  String deploymentId3=deployStartMessageTestProcess();
  String deploymentId4=deployProcessWithoutEvents();
  try {
    runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId2,true);
  repositoryService.deleteDeployment(deploymentId3,true);
  try {
    runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
  repositoryService.deleteDeployment(deploymentId1,true);
  try {
    runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
  cleanup(deploymentId4);
}
