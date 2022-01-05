@Test @org.activiti.engine.test.Deployment public void testSimpleTimerBoundaryEventTimerDoesNotFire(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleBoundaryTimer");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  assertEquals(1,managementService.createTimerJobQuery().count());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("The famous task",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,managementService.createTimerJobQuery().count());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
