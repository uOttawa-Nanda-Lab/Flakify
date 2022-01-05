@Deployment public void testConcurrentEndOfSameProcess() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskWithDelay");
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  TaskCompleter taskCompleter1=new TaskCompleter(task.getId());
  TaskCompleter taskCompleter2=new TaskCompleter(task.getId());
  assertFalse(taskCompleter1.isSucceeded());
  assertFalse(taskCompleter2.isSucceeded());
  taskCompleter1.start();
  taskCompleter2.start();
  taskCompleter1.join();
  taskCompleter2.join();
  int successCount=0;
  if (taskCompleter1.isSucceeded()) {
    successCount++;
  }
  if (taskCompleter2.isSucceeded()) {
    successCount++;
  }
  assertEquals("(Only) one thread should have been able to successfully end the process",1,successCount);
  assertProcessEnded(processInstance.getId());
}
