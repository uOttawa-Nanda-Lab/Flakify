@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testSuspendActivateProcessDefinitionByKey(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
  repositoryService.suspendProcessDefinitionByKey(processDefinition.getKey());
  processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertTrue(processDefinition.isSuspended());
  repositoryService.activateProcessDefinitionByKey(processDefinition.getKey());
  processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
}
