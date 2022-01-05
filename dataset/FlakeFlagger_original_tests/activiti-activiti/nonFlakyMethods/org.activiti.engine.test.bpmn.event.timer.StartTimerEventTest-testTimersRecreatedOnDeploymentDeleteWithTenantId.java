public void testTimersRecreatedOnDeploymentDeleteWithTenantId(){
  for (int i=1; i <= 4; i++) {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v" + i + ".bpmn20.xml").deploy();
  }
  String testTenant="Activiti-tenant";
  String deployment1=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v1.bpmn20.xml").tenantId(testTenant).deploy().getId();
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  String deployment2=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v2.bpmn20.xml").tenantId(testTenant).deploy().getId();
  assertEquals(2,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  String deployment3=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v3.bpmn20.xml").tenantId(testTenant).deploy().getId();
  assertEquals(3,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  String deployment4=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v4.bpmn20.xml").tenantId(testTenant).deploy().getId();
  assertEquals(4,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(4,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  repositoryService.deleteDeployment(deployment4,true);
  assertEquals(3,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  repositoryService.deleteDeployment(deployment2,true);
  assertEquals(2,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(0,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  repositoryService.deleteDeployment(deployment3,true);
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(testTenant).count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(testTenant).count());
  assertEquals(1,managementService.createTimerJobQuery().jobTenantId(testTenant).count());
  for (  ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().processDefinitionKey("timer").orderByProcessDefinitionVersion().desc().list()) {
    repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
  }
  assertEquals(0,managementService.createTimerJobQuery().count());
}
