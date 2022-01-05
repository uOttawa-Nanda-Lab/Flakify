public void testChangeDeploymentIdWithClash(){
  String processDefinitionIdWithTenant=deployTestProcessWithTestTenant("tenantA");
  deployOneTaskTestProcess();
  try {
    repositoryService.changeDeploymentTenantId(processDefinitionIdWithTenant,"");
    fail();
  }
 catch (  Exception e) {
  }
  String processDefinitionIdNoTenant2=deployOneTaskTestProcess();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdNoTenant2).singleResult().getVersion());
}
