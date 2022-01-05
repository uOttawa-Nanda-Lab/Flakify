@Deployment public void testNestedParallelSubProcessWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelSubProcess").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(12,tasks.size());
  for (int i=0; i < 3; i++) {
    taskService.complete(tasks.get(i).getId());
  }
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
