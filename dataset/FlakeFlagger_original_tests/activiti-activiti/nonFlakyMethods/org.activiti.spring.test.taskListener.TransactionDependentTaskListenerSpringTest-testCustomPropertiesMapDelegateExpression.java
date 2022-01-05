@Deployment public void testCustomPropertiesMapDelegateExpression(){
  runtimeService.startProcessInstanceByKey("transactionDependentTaskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task3",listener.getCurrentTasks().get(0).getTaskId());
  assertEquals("task3",listener.getCurrentTasks().get(0).getCustomPropertiesMap().get("customProp1"));
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task4",listener.getCurrentTasks().get(1).getTaskId());
  assertEquals("task4",listener.getCurrentTasks().get(1).getCustomPropertiesMap().get("customProp1"));
}
