@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testNestedParallelCallActivityWithTimer.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testNestedParallelCallActivityWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelCallActivityWithTimer").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(4,tasks.size());
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
