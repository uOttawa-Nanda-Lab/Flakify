@Deployment public void testOnCompleteCommitted(){
  CurrentTaskTransactionDependentTaskListener.clear();
  Map<String,Object> variables=new HashMap<>();
  variables.put("serviceTask1",false);
  variables.put("serviceTask2",false);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenersOnCompleteCommitted",variables);
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks=CurrentTaskTransactionDependentTaskListener.getCurrentTasks();
  assertEquals(1,currentTasks.size());
  assertEquals("usertask1",currentTasks.get(0).getTaskId());
  assertEquals("User Task 1",currentTasks.get(0).getTaskName());
  assertEquals(processInstance.getId(),currentTasks.get(0).getProcessInstanceId());
  assertNotNull(currentTasks.get(0).getProcessInstanceId());
}
