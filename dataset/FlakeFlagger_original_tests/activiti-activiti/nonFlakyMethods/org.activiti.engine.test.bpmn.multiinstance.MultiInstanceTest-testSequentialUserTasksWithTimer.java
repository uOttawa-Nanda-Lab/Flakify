@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.sequentialUserTasks.bpmn20.xml"}) public void testSequentialUserTasksWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialUserTasks",CollectionUtil.singletonMap(NR_OF_LOOPS_KEY,3)).getId();
  taskService.complete(taskService.createTaskQuery().singleResult().getId());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
