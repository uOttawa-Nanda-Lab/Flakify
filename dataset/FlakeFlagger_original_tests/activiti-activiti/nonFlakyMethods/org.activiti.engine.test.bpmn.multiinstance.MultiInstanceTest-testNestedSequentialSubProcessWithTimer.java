@Deployment public void testNestedSequentialSubProcessWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedSequentialSubProcessWithTimer").getId();
  for (int i=0; i < 2; i++) {
    List<Task> tasks=taskService.createTaskQuery().taskAssignee("kermit").list();
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
  }
  List<Task> tasks=taskService.createTaskQuery().taskAssignee("kermit").list();
  taskService.complete(tasks.get(0).getId());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
