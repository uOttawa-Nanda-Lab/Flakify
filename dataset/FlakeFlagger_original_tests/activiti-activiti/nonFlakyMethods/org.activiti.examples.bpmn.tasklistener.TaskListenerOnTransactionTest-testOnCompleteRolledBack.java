@Deployment public void testOnCompleteRolledBack(){
  CurrentTaskTransactionDependentTaskListener.clear();
  Map<String,Object> variables=new HashMap<>();
  variables.put("serviceTask1",false);
  variables.put("serviceTask2",false);
  variables.put("serviceTask3",true);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenersOnCompleteCommitted",variables);
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  try {
    taskService.complete(task.getId());
  }
 catch (  Exception ex) {
  }
  List<CurrentTaskTransactionDependentTaskListener.CurrentTask> currentTasks=CurrentTaskTransactionDependentTaskListener.getCurrentTasks();
  assertEquals(2,currentTasks.size());
  assertEquals("usertask1",currentTasks.get(0).getTaskId());
  assertEquals("User Task 1",currentTasks.get(0).getTaskName());
  assertEquals(processInstance.getId(),currentTasks.get(0).getProcessInstanceId());
  assertNotNull(currentTasks.get(0).getProcessInstanceId());
  assertEquals("usertask3",currentTasks.get(1).getTaskId());
  assertEquals("User Task 3",currentTasks.get(1).getTaskName());
  assertEquals(processInstance.getId(),currentTasks.get(1).getProcessInstanceId());
  assertNotNull(currentTasks.get(1).getProcessInstanceId());
}
