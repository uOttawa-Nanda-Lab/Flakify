@Deployment public void testOnCompleteCustomPropertiesResolver(){
  CurrentTaskTransactionDependentTaskListener.clear();
  runtimeService.startProcessInstanceByKey("transactionDependentTaskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks=CurrentTaskTransactionDependentTaskListener.getCurrentTasks();
  assertEquals(1,currentTasks.size());
  assertEquals("usertask1",currentTasks.get(0).getTaskId());
  assertEquals("User Task 1",currentTasks.get(0).getTaskName());
  assertEquals(1,currentTasks.get(0).getCustomPropertiesMap().size());
  assertEquals("usertask1",currentTasks.get(0).getCustomPropertiesMap().get("customProp1"));
}
