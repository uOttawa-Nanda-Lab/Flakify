@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTaskQueryAfterProcessInstanceSuspend(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  task=taskService.createTaskQuery().active().singleResult();
  assertNotNull(task);
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  assertEquals(1,taskService.createTaskQuery().suspended().count());
  assertEquals(0,taskService.createTaskQuery().active().count());
  runtimeService.activateProcessInstanceById(processInstance.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  assertEquals(0,taskService.createTaskQuery().suspended().count());
  assertEquals(1,taskService.createTaskQuery().active().count());
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
