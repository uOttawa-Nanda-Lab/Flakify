@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testSuspendActivateProcessDefinitionById(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertTrue(processDefinition.isSuspended());
  repositoryService.activateProcessDefinitionById(processDefinition.getId());
  processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
}
