public void testProcessDefinitionTenancy(){
  String processDefinitionIdWithTenant=deployTestProcessWithTestTenant();
  assertEquals(TEST_TENANT_ID,repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdWithTenant).singleResult().getTenantId());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%").list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
  String processDefinitionIdWithoutTenant=deployOneTaskTestProcess();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%").list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
  String processDefinitionIdWithTenant2=deployTestProcessWithTestTenant();
  assertEquals(3,repositoryService.createProcessDefinitionQuery().list().size());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(TEST_TENANT_ID).list().size());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("m%").list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionTenantIdLike("somethingElse%").list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().list().size());
  assertEquals(processDefinitionIdWithTenant2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(TEST_TENANT_ID).latestVersion().singleResult().getId());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId("Not a tenant").latestVersion().count());
  assertEquals(processDefinitionIdWithoutTenant,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionWithoutTenantId().latestVersion().singleResult().getId());
}
