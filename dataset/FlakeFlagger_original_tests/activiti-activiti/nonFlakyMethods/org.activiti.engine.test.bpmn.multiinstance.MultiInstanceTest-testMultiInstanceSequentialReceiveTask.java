@Deployment public void testMultiInstanceSequentialReceiveTask(){
  runtimeService.startProcessInstanceByKey("multi-instance-receive");
  Execution execution=runtimeService.createExecutionQuery().activityId("theReceiveTask").singleResult();
  assertNotNull(execution);
  while (execution != null) {
    runtimeService.trigger(execution.getId());
    execution=runtimeService.createExecutionQuery().activityId("theReceiveTask").singleResult();
  }
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
