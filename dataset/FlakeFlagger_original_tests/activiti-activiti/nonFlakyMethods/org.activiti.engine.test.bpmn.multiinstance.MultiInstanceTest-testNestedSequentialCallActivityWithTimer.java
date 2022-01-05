@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testNestedSequentialCallActivityWithTimer.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testNestedSequentialCallActivityWithTimer(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedSequentialCallActivityWithTimer").getId();
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("task one",tasks.get(0).getName());
  assertEquals("task two",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  taskService.complete(tasks.get(0).getId());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task taskAfterTimer=taskService.createTaskQuery().singleResult();
  assertEquals("taskAfterTimer",taskAfterTimer.getTaskDefinitionKey());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(procId);
}
