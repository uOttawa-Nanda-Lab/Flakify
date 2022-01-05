@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testSuspendActivateProcessInstance(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertFalse(processInstance.isSuspended());
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertTrue(processInstance.isSuspended());
  runtimeService.activateProcessInstanceById(processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  assertFalse(processInstance.isSuspended());
}
