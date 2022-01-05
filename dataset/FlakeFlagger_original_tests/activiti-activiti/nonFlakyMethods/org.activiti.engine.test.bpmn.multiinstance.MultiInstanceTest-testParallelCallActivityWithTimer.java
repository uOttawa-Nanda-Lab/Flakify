@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelCallActivityWithTimer.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testParallelCallActivityWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelCallActivity").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(6,tasks.size());
  for (int i=0; i < 2; i++) {
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
