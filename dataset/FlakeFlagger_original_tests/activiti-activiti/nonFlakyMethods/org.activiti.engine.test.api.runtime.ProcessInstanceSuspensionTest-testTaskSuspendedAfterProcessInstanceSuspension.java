@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testTaskSuspendedAfterProcessInstanceSuspension(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  runtimeService.startProcessInstanceByKey(processDefinition.getKey());
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().singleResult();
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  for (  Task task : tasks) {
    assertTrue(task.isSuspended());
  }
  runtimeService.activateProcessInstanceById(processInstance.getId());
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  for (  Task task : tasks) {
    assertFalse(task.isSuspended());
  }
}
