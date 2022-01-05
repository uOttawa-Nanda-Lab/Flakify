public void testTimersRecreatedOnDeploymentDelete(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v1.bpmn20.xml").deploy().getId();
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  String deployment2=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v2.bpmn20.xml").deploy().getId();
  assertEquals(2,repositoryService.createDeploymentQuery().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  String deployment3=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v3.bpmn20.xml").deploy().getId();
  assertEquals(3,repositoryService.createDeploymentQuery().count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  String deployment4=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testTimersRecreatedOnDeploymentDelete_v4.bpmn20.xml").deploy().getId();
  assertEquals(4,repositoryService.createDeploymentQuery().count());
  assertEquals(4,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  repositoryService.deleteDeployment(deployment4,true);
  assertEquals(3,repositoryService.createDeploymentQuery().count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  repositoryService.deleteDeployment(deployment2,true);
  assertEquals(2,repositoryService.createDeploymentQuery().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  repositoryService.deleteDeployment(deployment3,true);
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  for (  ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().processDefinitionKey("timer").orderByProcessDefinitionVersion().desc().list()) {
    repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
  }
  assertEquals(0,managementService.createTimerJobQuery().count());
}
