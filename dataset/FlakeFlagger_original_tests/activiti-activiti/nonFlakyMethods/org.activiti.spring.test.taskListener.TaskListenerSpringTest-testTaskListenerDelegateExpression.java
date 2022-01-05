@Deployment public void testTaskListenerDelegateExpression(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerDelegateExpression");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task1-complete",runtimeService.getVariable(processInstance.getId(),"calledInExpression"));
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task2-notify",runtimeService.getVariable(processInstance.getId(),"calledThroughNotify"));
}
