@Deployment public void testTimerOnConcurrentTasks(){
  String procId=runtimeService.startProcessInstanceByKey("nonInterruptingOnConcurrentTasks").getId();
  assertEquals(2,taskService.createTaskQuery().count());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  assertEquals(3,taskService.createTaskQuery().count());
  Task task=taskService.createTaskQuery().taskDefinitionKey("timerFiredTask").singleResult();
  taskService.complete(task.getId());
  assertEquals(2,taskService.createTaskQuery().count());
  for (  Task t : taskService.createTaskQuery().list()) {
    taskService.complete(t.getId());
  }
  assertProcessEnded(procId);
}
