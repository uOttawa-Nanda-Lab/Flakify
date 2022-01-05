@Deployment public void testSequentialSubProcessWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialSubprocessWithTimer").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
