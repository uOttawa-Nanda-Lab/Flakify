@Deployment public void testCustomPropertiesMapDelegateExpression(){
  runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task3",listener.getCurrentActivities().get(0).getActivityId());
  assertEquals("task3",listener.getCurrentActivities().get(0).getCustomPropertiesMap().get("customProp1"));
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("task4",listener.getCurrentActivities().get(1).getActivityId());
  assertEquals("task4",listener.getCurrentActivities().get(1).getCustomPropertiesMap().get("customProp1"));
}
