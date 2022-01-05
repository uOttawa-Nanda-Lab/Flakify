public void testStartProcessInstanceBySignalTenancy(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testStartProcessInstanceBySignalTenancy.bpmn20.xml").deploy();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testStartProcessInstanceBySignalTenancy.bpmn20.xml").tenantId(TEST_TENANT_ID).deploy();
  runtimeService.signalEventReceived("The Signal");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEquals(3,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).count());
  runtimeService.signalEventReceivedWithTenantId("The Signal",TEST_TENANT_ID);
  assertEquals(6,runtimeService.createProcessInstanceQuery().count());
  assertEquals(3,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().count());
  assertEquals(3,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).count());
  runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  runtimeService.startProcessInstanceByKeyAndTenantId("processWithSignalCatch",TEST_TENANT_ID);
  runtimeService.signalEventReceived("The Signal");
  assertEquals(11,runtimeService.createProcessInstanceQuery().count());
  assertEquals(7,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().count());
  assertEquals(4,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).count());
  runtimeService.signalEventReceivedWithTenantId("The Signal",TEST_TENANT_ID);
  assertEquals(14,runtimeService.createProcessInstanceQuery().count());
  assertEquals(7,runtimeService.createProcessInstanceQuery().processInstanceWithoutTenantId().count());
  assertEquals(7,runtimeService.createProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).count());
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
