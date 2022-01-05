public void testChangeDeploymentTenantId(){
  String processDefinitionIdWithTenant=deployTestProcessWithTwoTasksWithTestTenant();
  int nrOfProcessInstancesWithTenant=4;
  for (int i=0; i < nrOfProcessInstancesWithTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdWithTenant);
  }
  String processDefinitionIdNoTenant=deployTwoTasksTestProcess();
  int nrOfProcessInstancesNoTenant=5;
  for (int i=0; i < nrOfProcessInstancesNoTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
  }
  String newTenantId="NEW TENANT ID";
  String deploymentId=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdWithTenant).singleResult().getDeploymentId();
  repositoryService.changeDeploymentTenantId(deploymentId,newTenantId);
  Deployment deployment=repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
  assertEquals(newTenantId,deployment.getTenantId());
  assertEquals(2,repositoryService.createDeploymentQuery().list().size());
  assertEquals(0,repositoryService.createDeploymentQuery().deploymentTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(newTenantId).list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentWithoutTenantId().list().size());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(newTenantId).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(newTenantId).list().size());
  assertEquals(nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().list().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());
  assertEquals(nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().processInstanceTenantId(newTenantId).list().size());
  assertEquals(nrOfProcessInstancesNoTenant,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().list().size());
  assertEquals(3 * (nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant),runtimeService.createExecutionQuery().list().size());
  assertEquals(3 * nrOfProcessInstancesNoTenant,runtimeService.createExecutionQuery().executionWithoutTenantId().list().size());
  assertEquals(0,runtimeService.createExecutionQuery().executionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(3 * nrOfProcessInstancesWithTenant,runtimeService.createExecutionQuery().executionTenantId(newTenantId).list().size());
  assertEquals(3 * nrOfProcessInstancesWithTenant,runtimeService.createExecutionQuery().executionTenantIdLike("NEW%").list().size());
  assertEquals(2 * (nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant),taskService.createTaskQuery().list().size());
  assertEquals(0,taskService.createTaskQuery().taskTenantId(TEST_TENANT_ID).list().size());
  assertEquals(2 * nrOfProcessInstancesWithTenant,taskService.createTaskQuery().taskTenantId(newTenantId).list().size());
  assertEquals(2 * nrOfProcessInstancesNoTenant,taskService.createTaskQuery().taskWithoutTenantId().list().size());
  try {
    repositoryService.changeDeploymentTenantId(deploymentId,"");
    fail();
  }
 catch (  Exception e) {
  }
}
