public void testProcessInstanceTenancy(){
  String processDefinitionId=deployTestProcessWithTestTenant();
  int nrOfProcessInstancesWithTenant=6;
  for (int i=0; i < nrOfProcessInstancesWithTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionId);
  }
  String processDefinitionIdNoTenant=deployOneTaskTestProcess();
  int nrOfProcessInstancesNoTenant=8;
  for (int i=0; i < nrOfProcessInstancesNoTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
  }
  assertEquals(TEST_TENANT_ID,runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list().get(0).getTenantId());
  assertEquals(nrOfProcessInstancesNoTenant + nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().list().size());
  assertEquals(nrOfProcessInstancesNoTenant,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().list().size());
  assertEquals(nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());
  assertEquals(nrOfProcessInstancesWithTenant,runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%enan%").list().size());
}
