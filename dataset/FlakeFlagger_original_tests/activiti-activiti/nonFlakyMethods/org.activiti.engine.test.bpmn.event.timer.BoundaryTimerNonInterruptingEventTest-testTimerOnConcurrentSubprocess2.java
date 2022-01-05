@Deployment(resources="org/activiti/engine/test/bpmn/event/timer/BoundaryTimerNonInterruptingEventTest.testTimerOnConcurrentSubprocess.bpmn20.xml") public void testTimerOnConcurrentSubprocess2(){
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
  task=taskService.createTaskQuery().taskDefinitionKey("timerFiredTask").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("sub2task1").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("sub2task2").singleResult();
  taskService.complete(task.getId());
  assertEquals(0,taskService.createTaskQuery().count());
  assertProcessEnded(procId);
}
