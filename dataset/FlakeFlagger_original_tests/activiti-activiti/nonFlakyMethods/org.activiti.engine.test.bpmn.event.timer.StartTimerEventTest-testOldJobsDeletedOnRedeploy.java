public void testOldJobsDeletedOnRedeploy(){
  for (int i=0; i < 3; i++) {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testOldJobsDeletedOnRedeploy.bpmn20.xml").deploy();
    assertEquals(i + 1,repositoryService.createDeploymentQuery().count());
    assertEquals(i + 1,repositoryService.createProcessDefinitionQuery().count());
    assertEquals(1,managementService.createTimerJobQuery().count());
  }
  for (  ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().processDefinitionKey("timer").orderByProcessDefinitionVersion().desc().list()) {
    repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
  }
  assertEquals(0,managementService.createTimerJobQuery().count());
  assertEquals(0,managementService.createJobQuery().count());
}
