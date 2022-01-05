@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testCannotActivateActiveProcessInstance(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertFalse(processInstance.isSuspended());
  try {
    runtimeService.activateProcessInstanceById(processInstance.getId());
    fail("Expected activiti exception");
  }
 catch (  ActivitiException e) {
  }
}
