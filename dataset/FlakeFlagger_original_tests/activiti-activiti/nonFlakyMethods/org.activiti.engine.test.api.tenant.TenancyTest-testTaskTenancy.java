public void testTaskTenancy(){
  String processDefinitionIdWithTenant=deployTestProcessWithTwoTasksWithTestTenant();
  int nrOfProcessInstancesWithTenant=5;
  for (int i=0; i < nrOfProcessInstancesWithTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdWithTenant);
  }
  String processDefinitionIdNoTenant=deployTwoTasksTestProcess();
  int nrOfProcessInstancesNoTenant=2;
  for (int i=0; i < nrOfProcessInstancesNoTenant; i++) {
    runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
  }
  assertEquals(TEST_TENANT_ID,taskService.createTaskQuery().processDefinitionId(processDefinitionIdWithTenant).list().get(0).getTenantId());
  assertEquals("",taskService.createTaskQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());
  assertEquals(14,taskService.createTaskQuery().list().size());
  assertEquals(10,taskService.createTaskQuery().taskTenantId(TEST_TENANT_ID).list().size());
  assertEquals(0,taskService.createTaskQuery().taskTenantId("Another").list().size());
  assertEquals(10,taskService.createTaskQuery().taskTenantIdLike("my%").list().size());
  assertEquals(4,taskService.createTaskQuery().taskWithoutTenantId().list().size());
}
