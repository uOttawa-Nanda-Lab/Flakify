@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml"}) public void testProcessDefinitionActiveByDefault(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertFalse(processDefinition.isSuspended());
}
