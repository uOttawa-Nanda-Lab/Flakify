@Deployment public void testParallelSubProcessWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelSubprocessWithTimer").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(6,tasks.size());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
