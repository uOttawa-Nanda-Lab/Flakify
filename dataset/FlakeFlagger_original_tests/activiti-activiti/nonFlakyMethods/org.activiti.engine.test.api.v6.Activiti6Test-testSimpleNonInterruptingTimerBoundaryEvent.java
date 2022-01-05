@Test @org.activiti.engine.test.Deployment public void testSimpleNonInterruptingTimerBoundaryEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleBoundaryTimer");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Job job=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
  processInstance=runtimeService.startProcessInstanceByKey("simpleBoundaryTimer");
  job=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  tasks=taskService.createTaskQuery().orderByTaskName().desc().list();
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
