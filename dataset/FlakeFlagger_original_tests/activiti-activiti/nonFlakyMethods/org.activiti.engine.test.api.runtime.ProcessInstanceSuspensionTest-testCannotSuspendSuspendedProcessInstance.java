@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testCannotSuspendSuspendedProcessInstance(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertFalse(processInstance.isSuspended());
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  try {
    runtimeService.suspendProcessInstanceById(processInstance.getId());
    fail("Expected activiti exception");
  }
 catch (  ActivitiException e) {
  }
}
