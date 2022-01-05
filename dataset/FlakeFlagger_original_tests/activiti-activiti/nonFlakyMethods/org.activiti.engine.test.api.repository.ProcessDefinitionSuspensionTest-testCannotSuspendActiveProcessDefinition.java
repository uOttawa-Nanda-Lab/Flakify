@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testCannotSuspendActiveProcessDefinition(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  try {
    repositoryService.suspendProcessDefinitionById(processDefinition.getId());
    fail("Exception expected");
  }
 catch (  ActivitiException e) {
  }
}
