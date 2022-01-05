public void testProcessDefinitionKeyClashBetweenTenants(){
  String tentanA="tenantA";
  String tenantB="tenantB";
  String procDefIdA=deployTestProcessWithTestTenant(tentanA);
  String procDefIdB=deployTestProcessWithTestTenant(tenantB);
  assertEquals("oneTaskProcess",repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdA).singleResult().getKey());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdA).singleResult().getVersion());
  assertEquals("oneTaskProcess",repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdB).singleResult().getKey());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdB).singleResult().getVersion());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tentanA).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tenantB).list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionWithoutTenantId().list().size());
  procDefIdA=deployTestProcessWithTestTenant(tentanA);
  assertEquals("oneTaskProcess",repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdA).singleResult().getKey());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdA).singleResult().getVersion());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tentanA).latestVersion().singleResult().getVersion());
  assertEquals("oneTaskProcess",repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdB).singleResult().getKey());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefIdB).singleResult().getVersion());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").list().size());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tentanA).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tenantB).list().size());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionTenantId(tentanA).latestVersion().list().size());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").processDefinitionWithoutTenantId().list().size());
  try {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
    fail();
  }
 catch (  Exception e) {
  }
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("oneTaskProcess",tentanA);
  assertEquals(procDefIdA,processInstance.getProcessDefinitionId());
  processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("oneTaskProcess",tenantB);
  assertEquals(procDefIdB,processInstance.getProcessDefinitionId());
}
