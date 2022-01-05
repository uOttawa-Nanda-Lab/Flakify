public void testStartProcessInstanceByMessageTenancy(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testMessageTenancy.bpmn20.xml").deploy();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testMessageTenancy.bpmn20.xml").tenantId(TEST_TENANT_ID).deploy();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("My message").count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("My message").processDefinitionWithoutTenantId().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("My message").processDefinitionTenantId(TEST_TENANT_ID).count());
  runtimeService.startProcessInstanceByMessage("My message");
  runtimeService.startProcessInstanceByMessage("My message");
  assertEquals(2,taskService.createTaskQuery().taskName("My task").count());
  assertEquals(2,taskService.createTaskQuery().taskName("My task").taskWithoutTenantId().count());
  assertEquals(0,taskService.createTaskQuery().taskName("My task").taskTenantId(TEST_TENANT_ID).count());
  runtimeService.startProcessInstanceByMessageAndTenantId("My message",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByMessageAndTenantId("My message",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByMessageAndTenantId("My message",TEST_TENANT_ID);
  assertEquals(5,taskService.createTaskQuery().taskName("My task").count());
  assertEquals(2,taskService.createTaskQuery().taskName("My task").taskWithoutTenantId().count());
  assertEquals(3,taskService.createTaskQuery().taskName("My task").taskTenantId(TEST_TENANT_ID).count());
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
