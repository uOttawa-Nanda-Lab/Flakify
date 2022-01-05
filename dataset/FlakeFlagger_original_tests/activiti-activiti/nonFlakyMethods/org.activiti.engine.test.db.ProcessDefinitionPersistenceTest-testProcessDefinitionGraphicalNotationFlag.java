public void testProcessDefinitionGraphicalNotationFlag(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/db/process-with-di.bpmn20.xml").addClasspathResource("org/activiti/engine/test/db/process-without-di.bpmn20.xml").deploy().getId();
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  ProcessDefinition processWithDi=repositoryService.createProcessDefinitionQuery().processDefinitionKey("processWithDi").singleResult();
  assertTrue(processWithDi.hasGraphicalNotation());
  ProcessDefinition processWithoutDi=repositoryService.createProcessDefinitionQuery().processDefinitionKey("processWithoutDi").singleResult();
  assertFalse(processWithoutDi.hasGraphicalNotation());
  repositoryService.deleteDeployment(deploymentId);
}
