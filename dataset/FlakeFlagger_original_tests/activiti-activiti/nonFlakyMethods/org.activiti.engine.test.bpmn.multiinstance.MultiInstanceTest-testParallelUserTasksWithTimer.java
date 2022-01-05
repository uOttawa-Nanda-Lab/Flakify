@Deployment public void testParallelUserTasksWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelUserTasksWithTimer").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  taskService.complete(tasks.get(0).getId());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
