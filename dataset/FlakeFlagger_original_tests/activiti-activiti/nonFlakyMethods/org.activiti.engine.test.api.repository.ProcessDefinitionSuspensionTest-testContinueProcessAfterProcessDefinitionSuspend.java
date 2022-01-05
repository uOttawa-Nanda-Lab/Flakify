@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testContinueProcessAfterProcessDefinitionSuspend(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
