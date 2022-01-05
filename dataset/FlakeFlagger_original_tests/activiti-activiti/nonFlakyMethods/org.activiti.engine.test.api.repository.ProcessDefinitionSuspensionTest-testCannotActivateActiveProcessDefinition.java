@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testCannotActivateActiveProcessDefinition(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
  try {
    repositoryService.activateProcessDefinitionById(processDefinition.getId());
    fail("Exception expected");
  }
 catch (  ActivitiException e) {
  }
}
