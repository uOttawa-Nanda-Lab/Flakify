public void testGetLatestProcessDefinitionVersionForSameProcessDefinitionKey(){
  String tenant1="tenant1";
  String tenant2="tenant2";
  for (int i=0; i < 4; i++) {
    deployTestProcessWithTestTenant(tenant1);
  }
  for (int i=0; i < 2; i++) {
    deployTestProcessWithTestTenant(tenant2);
  }
  for (int i=0; i < 3; i++) {
    deployTestProcessWithTwoTasksNoTenant();
  }
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenant1).latestVersion().singleResult();
  assertEquals(4,processDefinition.getVersion());
  processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(tenant2).latestVersion().singleResult();
  assertEquals(2,processDefinition.getVersion());
  processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionWithoutTenantId().latestVersion().singleResult();
  assertEquals(3,processDefinition.getVersion());
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().latestVersion().list();
  assertEquals(3,processDefinitions.size());
  int tenant1Count=0, tenant2Count=0, noTenantCount=0;
  for (  ProcessDefinition p : processDefinitions) {
    if (p.getTenantId() == null || p.getTenantId().equals(ProcessEngineConfiguration.NO_TENANT_ID)) {
      noTenantCount++;
    }
 else     if (p.getTenantId().equals(tenant1)) {
      tenant1Count++;
    }
 else     if (p.getTenantId().equals(tenant2)) {
      tenant2Count++;
    }
  }
  assertEquals(1,tenant1Count);
  assertEquals(1,tenant2Count);
  assertEquals(1,noTenantCount);
}
