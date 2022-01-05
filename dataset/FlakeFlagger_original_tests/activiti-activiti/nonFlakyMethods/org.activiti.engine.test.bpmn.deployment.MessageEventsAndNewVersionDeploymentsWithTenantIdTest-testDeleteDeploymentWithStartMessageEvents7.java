public void testDeleteDeploymentWithStartMessageEvents7(){
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
  repositoryService.deleteDeployment(deploymentId4,true);
  runtimeService.startProcessInstanceByMessageAndTenantId("myStartMessage",TENANT_ID);
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  cleanup(deploymentId1);
}
