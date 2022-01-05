public void testSignalThroughApiTenancy(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testMultiTenancySignals.bpmn20.xml").deploy();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testMultiTenancySignals.bpmn20.xml").tenantId(TEST_TENANT_ID).deploy();
  runtimeService.startProcessInstanceByKeyAndTenantId("testMtSignalCatch",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByKeyAndTenantId("testMtSignalCatch",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByKeyAndTenantId("testMtSignalCatch",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByKeyAndTenantId("testMtSignalCatch",TEST_TENANT_ID);
  runtimeService.startProcessInstanceByKey("testMtSignalCatch");
  runtimeService.startProcessInstanceByKey("testMtSignalCatch");
  runtimeService.startProcessInstanceByKey("testMtSignalCatch");
  runtimeService.startProcessInstanceByKey("testMtSignalCatch");
  runtimeService.startProcessInstanceByKey("testMtSignalCatch");
  assertEquals(4,taskService.createTaskQuery().taskName("My task").taskTenantId(TEST_TENANT_ID).count());
  assertEquals(5,taskService.createTaskQuery().taskName("My task").taskWithoutTenantId().count());
  runtimeService.signalEventReceivedWithTenantId("The Signal",TEST_TENANT_ID);
  assertEquals(4,taskService.createTaskQuery().taskName("Task after signal").taskTenantId(TEST_TENANT_ID).count());
  assertEquals(0,taskService.createTaskQuery().taskName("Task after signal").taskWithoutTenantId().count());
  runtimeService.signalEventReceived("The Signal");
  assertEquals(4,taskService.createTaskQuery().taskName("Task after signal").taskTenantId(TEST_TENANT_ID).count());
  assertEquals(5,taskService.createTaskQuery().taskName("Task after signal").taskWithoutTenantId().count());
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
