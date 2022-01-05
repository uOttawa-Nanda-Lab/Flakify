@Test @org.activiti.engine.test.Deployment public void testSimpleTimerBoundaryEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleBoundaryTimer");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Job job=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task after timer",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
