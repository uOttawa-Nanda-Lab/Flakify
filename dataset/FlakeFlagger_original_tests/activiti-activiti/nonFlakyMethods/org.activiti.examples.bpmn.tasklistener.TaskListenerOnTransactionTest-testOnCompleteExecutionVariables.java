@Deployment public void testOnCompleteExecutionVariables(){
  CurrentTaskTransactionDependentTaskListener.clear();
  runtimeService.startProcessInstanceByKey("taskListenersOnCompleteExecutionVariables");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks=CurrentTaskTransactionDependentTaskListener.getCurrentTasks();
  assertEquals(2,currentTasks.size());
  assertEquals("usertask1",currentTasks.get(0).getTaskId());
  assertEquals("User Task 1",currentTasks.get(0).getTaskName());
  assertEquals(1,currentTasks.get(1).getExecutionVariables().size());
  assertEquals("test1",currentTasks.get(0).getExecutionVariables().get("injectedExecutionVariable"));
  assertEquals("usertask2",currentTasks.get(1).getTaskId());
  assertEquals("User Task 2",currentTasks.get(1).getTaskName());
  assertEquals(1,currentTasks.get(1).getExecutionVariables().size());
  assertEquals("test2",currentTasks.get(1).getExecutionVariables().get("injectedExecutionVariable"));
}
