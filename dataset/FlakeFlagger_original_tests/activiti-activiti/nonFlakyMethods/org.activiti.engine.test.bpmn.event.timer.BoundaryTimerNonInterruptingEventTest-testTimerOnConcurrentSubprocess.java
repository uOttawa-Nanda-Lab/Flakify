@Deployment public void testTimerOnConcurrentSubprocess(){
  String procId=runtimeService.startProcessInstanceByKey("testTimerOnConcurrentSubprocess").getId();
  assertEquals(4,taskService.createTaskQuery().count());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  assertEquals(5,taskService.createTaskQuery().count());
  Task task=taskService.createTaskQuery().taskDefinitionKey("sub1task1").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("sub1task2").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("sub2task1").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("sub2task2").singleResult();
  taskService.complete(task.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  task=taskService.createTaskQuery().taskDefinitionKey("timerFiredTask").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
