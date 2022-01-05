@Deployment public void testCompensateNestedSubprocess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("afterNestedSubProcess").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  Task compensationTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("undoBookHotel").singleResult();
  assertNotNull(compensationTask);
  taskService.complete(compensationTask.getId());
  assertProcessEnded(processInstance.getId());
}
