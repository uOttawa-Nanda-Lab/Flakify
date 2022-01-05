@Deployment public void testChangeFormKey(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  String processDefinitionId=processInstance.getProcessDefinitionId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test",task.getFormKey());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  ObjectNode infoNode=dynamicBpmnService.changeUserTaskFormKey("task1","test2");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicUserTask");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("test2",task.getFormKey());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
