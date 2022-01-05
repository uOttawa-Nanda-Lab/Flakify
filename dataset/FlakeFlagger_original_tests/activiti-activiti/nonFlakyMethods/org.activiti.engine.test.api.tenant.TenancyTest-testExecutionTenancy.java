public void testExecutionTenancy(){
  String processDefinitionId=deployTestProcessWithTwoTasksWithTestTenant();
  int nrOfProcessInstancesWithTenant=4;
  for (int i=0; i < nrOfProcessInstancesWithTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionId);
  }
  String processDefinitionIdNoTenant=deployTwoTasksTestProcess();
  int nrOfProcessInstancesNoTenant=2;
  for (int i=0; i < nrOfProcessInstancesNoTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
  }
  assertEquals(TEST_TENANT_ID,runtimeService.createExecutionQuery().processDefinitionId(processDefinitionId).list().get(0).getTenantId());
  assertEquals("",runtimeService.createExecutionQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());
  assertEquals(3 * (nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant),runtimeService.createExecutionQuery().list().size());
  assertEquals(3 * nrOfProcessInstancesNoTenant,runtimeService.createExecutionQuery().executionWithoutTenantId().list().size());
  assertEquals(3 * nrOfProcessInstancesWithTenant,runtimeService.createExecutionQuery().executionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(3 * nrOfProcessInstancesWithTenant,runtimeService.createExecutionQuery().executionTenantIdLike("%en%").list().size());
  assertEquals(TEST_TENANT_ID,runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list().get(0).getTenantId());
  assertEquals(nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().list().size());
  assertEquals(nrOfProcessInstancesNoTenant,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().list().size());
  assertEquals(nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());
  assertEquals(nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%en%").list().size());
}
