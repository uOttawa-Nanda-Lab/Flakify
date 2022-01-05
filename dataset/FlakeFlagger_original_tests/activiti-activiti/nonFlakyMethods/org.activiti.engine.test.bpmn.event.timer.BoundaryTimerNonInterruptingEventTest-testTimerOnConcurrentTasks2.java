@Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/BoundaryTimerNonInterruptingEventTest.testTimerOnConcurrentTasks.bpmn20.xml"}) public void testTimerOnConcurrentTasks2(){
  String procId=runtimeService.startProcessInstanceByKey("nonInterruptingOnConcurrentTasks").getId();
  assertEquals(2,taskService.createTaskQuery().count());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  assertEquals(3,taskService.createTaskQuery().count());
  Task task=taskService.createTaskQuery().taskDefinitionKey("firstTask").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskDefinitionKey("secondTask").singleResult();
  taskService.complete(task.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  task=taskService.createTaskQuery().taskDefinitionKey("timerFiredTask").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
